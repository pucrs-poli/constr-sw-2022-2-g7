package br.pucrs.classesms.filter;

import br.pucrs.classesms.component.TokenComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends GenericFilterBean {
    private final TokenComponent tokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            log.info("JWTFilter - requestURI == " + httpServletRequest.getRequestURI());
            String jwt = this.resolveToken(httpServletRequest);
            if (jwt == null) {
                log.error("JWTFilter - sem - Authorization no header");
                throw new Exception("sem - Authorization no header");
            }
//            if (this.tokenProvider.validateToken(jwt)) {
//                Authentication authentication = this.tokenProvider.getAuthentication(jwt);

//                if (authentication == null) {
//                   log.error("JWTFilter - this.tokenProvider.getAuthentication == null");
//                    throw new Exception("token_invalido");
//                }
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
            filterChain.doFilter(servletRequest, servletResponse);

            this.resetAuthenticationAfterRequest();
        } catch (Exception e) {
            response.setStatus(403);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(parserToJson(e.getMessage()));
            // throw new ServletException(e.getMessage());
        }
    }

    private void resetAuthenticationAfterRequest() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            String jwt = bearerToken.substring(7, bearerToken.length());
//            return jwt;
//        }
        return null;
    }

    private String parserToJson(String msg) {
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject = new JSONObject();
//
//        jsonObject.put("mensagem", msg);
//        jsonArray.add(jsonObject);
//        return jsonArray.toString();
        return null;
    }
}
