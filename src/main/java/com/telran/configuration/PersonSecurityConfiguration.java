package com.telran.configuration;

import com.telran.entity.PersonSession;
import com.telran.repository.PersonSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Configuration
public class PersonSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/login", "/api/register").permitAll()
                .antMatchers("/api/*").authenticated()
                .and()
                .logout().disable();

        http.addFilterBefore(securityFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter();
    }
}

class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private PersonSessionRepository personSessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null) {
            PersonSession personSession = personSessionRepository.findByIdAndIsValidTrue(header);
            if (personSession != null) {
                Authentication key = new UsernamePasswordAuthenticationToken(
                        personSession, // principal, @AuthenticatedUser
                        null,
                        new ArrayList<>()
                );

                SecurityContextHolder.getContext().setAuthentication(key);
            }

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
