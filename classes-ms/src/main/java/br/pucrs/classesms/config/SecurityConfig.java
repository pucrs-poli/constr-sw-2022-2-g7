package br.pucrs.classesms.config;

import br.pucrs.classesms.component.TokenComponent;
import br.pucrs.classesms.filter.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
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
        JWTFilter customFilter = new JWTFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
            .anyRequest()
            .fullyAuthenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf()
            .disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
            "/v2/api-docs",
            "/swagger-resources/**",
            "/user/getCurrentUserV2/**",
            "/user/getCurrentUserV3/**",
            "/admuserprovider/checkCredentialApplication/**",
            "/configuration/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/resources/**",
            "/resources/static/**",
            "/error/**",
            "/actuator/**");
    }
}
