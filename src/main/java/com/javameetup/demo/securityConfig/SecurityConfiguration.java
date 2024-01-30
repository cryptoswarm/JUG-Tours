package com.javameetup.demo.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/", "/index.html", "/static/**",
                                "/*.ico", "/*.json", "/*.png").permitAll()
                         .requestMatchers("/api/user").permitAll()
                         .anyRequest().authenticated()
                ).csrf((csrf) ->
                    csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                            .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                ).oauth2Login(Customizer.withDefaults());

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
