package com.thelab.spring.keycloak.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public static final String ADMIN = "client_admin";
    public static final String USER = "client_user";
    private final JwtConverter jwtConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((auth) ->
            auth.requestMatchers(HttpMethod.GET,"/api/hello").permitAll()
                    .requestMatchers(HttpMethod.GET,"/api/admin/**").hasRole(ADMIN)
                    .requestMatchers(HttpMethod.GET,"/api/user/**").hasRole(USER)
                    .requestMatchers(HttpMethod.GET,"/api/admin-and-user/**").hasAnyRole(ADMIN,USER)
                    .anyRequest().authenticated());

        http.sessionManagement((session) -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
        ));

        http.oauth2ResourceServer((oauth2) -> oauth2.jwt(
                jwt -> jwt.jwtAuthenticationConverter(jwtConverter)
        ));

        return http.build();
    }
}
