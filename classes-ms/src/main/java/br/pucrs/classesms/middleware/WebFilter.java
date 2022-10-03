package br.pucrs.classesms.middleware;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        List<String> allowedRoutes = Arrays.asList(

        );

        if (!allowedRoutes.contains(req.getServletPath())) {
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
