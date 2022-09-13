package br.pucrs.auth.config;

import br.pucrs.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static java.util.Objects.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (!req.getServletPath().equalsIgnoreCase("/login")) {
            String token = req.getHeader("Authorization");
            if (isNull(token)) {
                res.setStatus(401);
                res.getOutputStream().write("Token is missing!".getBytes());
                return;
            }
        }

//        if(!KeyValidator.valid(key)) {
//            res.setStatus(403);
//            res.getOutputStream().write("API Key is invalid".getBytes());
//            return;
//        }

        chain.doFilter(req, res);
    }
}
