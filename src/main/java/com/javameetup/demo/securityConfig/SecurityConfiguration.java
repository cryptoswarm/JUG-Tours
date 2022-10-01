package com.javameetup.demo.securityConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests((authorize) ->
                authorize.antMatchers("/**/*.{js,html,css}").permitAll()
                         .antMatchers("/", "/api/user").permitAll()
                         .anyRequest().authenticated()
                ).csrf((csrf) ->
                    csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                ).oauth2Login();

        return httpSecurity.build();
    }

    /**
     * The RequestCache bean overrides the default request cache
     * It saves the referrer header, so Spring Security can redirect back to it after authentication.
     * The referrer-based request cache comes in handy when you’re developing React on http://localhost:3000
     * and want to be redirected back there after logging in.
     * @return
     */
    @Bean
    public RequestCache refererRequestCache() {
        return new HttpSessionRequestCache() {
            @Override
            public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
                String referrer = request.getHeader("referer");
                if (referrer != null) {
                    request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST",
                            new SimpleSavedRequest(referrer));
                }
            }
        };
    }
}
