package br.pucrs.classesms.config;

import br.pucrs.classesms.middleware.TokenComponent;
import br.pucrs.classesms.middleware.impl.GenericFilterBeanImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenComponent tokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/",
                    "/v3/api-docs/**",
                    "/swagger-ui.html/**",
                    "/swagger-ui/**",
                    "/actuator/**").permitAll()
            .antMatchers("/classes/**").access("hasAnyAuthority('professores', '/professores')")
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(new GenericFilterBeanImpl(tokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
