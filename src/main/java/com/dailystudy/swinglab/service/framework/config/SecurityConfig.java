package com.dailystudy.swinglab.service.framework.config;

import com.dailystudy.swinglab.service.framework.auth.JwtTokenProvider;
import com.dailystudy.swinglab.service.framework.auth.filter.JwtAuthFilter;
import com.dailystudy.swinglab.service.framework.auth.handler.AuthAccessDeniedHandler;
import com.dailystudy.swinglab.service.framework.auth.handler.AuthExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig
{
    @Value("${security.ignoring}")
    private String[] ignoringUris;

    @Value("${security.permitAll}")
    private String[] permitAllUris;

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public HttpFirewall defaultHttpFirewall ()
    {
        return new DefaultHttpFirewall();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer ()
    {
        return (web) -> {
            web.httpFirewall(defaultHttpFirewall());
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
            web.ignoring().requestMatchers(ignoringUris);
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder ()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthExceptionEntryPoint authExceptionEntryPoint ()
    {
        return new AuthExceptionEntryPoint();
    }

    @Bean
    public AuthAccessDeniedHandler authAccessDeniedHandler ()
    {
        return new AuthAccessDeniedHandler();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter ()
    {
        return new JwtAuthFilter(jwtTokenProvider);
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.formLogin().disable();

        http.authorizeHttpRequests()
                .requestMatchers(permitAllUris).permitAll();

        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .addFilterBefore(jwtAuthFilter(), BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling()
                .authenticationEntryPoint(authExceptionEntryPoint())
                .accessDeniedHandler(authAccessDeniedHandler());

        http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
