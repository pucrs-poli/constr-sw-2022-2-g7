package br.pucrs.classesms.component.impl;

import br.pucrs.classesms.component.TokenComponent;
import br.pucrs.classesms.dto.response.UserInfoResponseDTO;
import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class GenericFilterBeanImpl extends OncePerRequestFilter {
    private final TokenComponent tokenProvider;
    private final List<RequestMatcher> excludedMatchers = List.of(
            new AntPathRequestMatcher("/"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/swagger-ui.html/**"),
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/actuator/**")
    );

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            log.info("requestURI == " + request.getRequestURI());

            String token = this.resolveToken(request);

            if (isNull(token)) {
                log.error("Sem Authorization no header");
                throw new Exception("sem - Authorization no header");
            }

            UserInfoResponseDTO userInfoResponseDTO = this.tokenProvider.getUserInfo(token);

            if (nonNull(userInfoResponseDTO)) {
                Authentication authentication = this.tokenProvider.getAuthentication(token, userInfoResponseDTO);

                if (isNull(authentication)) {
                    log.error("this.tokenProvider.getAuthentication == null");
                    throw new Exception("token_invalido");
                }
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

            this.resetAuthenticationAfterRequest();
        } catch (Exception e) {
            response.setStatus(403);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(parserToJson(e.getMessage()));
        }
    }

    @SneakyThrows
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return this.excludedMatchers.parallelStream().anyMatch(a -> a.matches(request));
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
