package br.pucrs.classesms.filter;

import br.pucrs.classesms.component.TokenComponent;
import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class JWTFilter extends GenericFilterBean {
    private final TokenComponent tokenProvider;

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            log.info("requestURI == " + httpServletRequest.getRequestURI());
            String jwt = this.resolveToken(httpServletRequest);

            if (isNull(jwt)) {
                log.error("Sem Authorization no header");
                throw new Exception("sem - Authorization no header");
            }

            if (this.tokenProvider.validateToken(jwt)) {
                Authentication authentication = this.tokenProvider.getAuthentication(jwt);

                if (isNull(authentication)) {
                   log.error("this.tokenProvider.getAuthentication == null");
                   throw new Exception("token_invalido");
                }
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(servletRequest, servletResponse);

            this.resetAuthenticationAfterRequest();
        } catch (Exception e) {
            response.setStatus(403);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(parserToJson(e.getMessage()));
//            throw new ServletException(e.getMessage());
        }
    }

    private void resetAuthenticationAfterRequest() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private String parserToJson(String msg) {
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensagem", msg);
        jsonArray.add(jsonObject);
        return jsonArray.toString();
    }
}
