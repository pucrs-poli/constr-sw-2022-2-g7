package br.pucrs.classesms.config;

import br.pucrs.classesms.component.TokenComponent;
//import br.pucrs.classesms.filter.JWTFilter;
import br.pucrs.classesms.filter.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenComponent tokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        JWTFilter customFilter = new JWTFilter(tokenProvider);
//        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/",
                        "/v3/api-docs/**",
                        "/swagger-ui.html/**",
                        "/swagger-ui/**",
                        "/actuator/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/classes").access("hasAnyRole('USER','ADMIN')")
                .antMatchers("/classes").permitAll()
//                .antMatchers("/classes").access("hasAnyRole('USER','ADMIN')")
                .anyRequest()
                .authenticated()
                .and().addFilterAfter(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
//                .and()
//                .addFilterAfter(new JWTLoginFilter("/users/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(new JWTAuthenticatorFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
