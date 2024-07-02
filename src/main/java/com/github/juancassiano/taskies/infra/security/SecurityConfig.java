package com.github.juancassiano.taskies.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
    return http
      .authorizeHttpRequests(
        authorizeConfig -> {
            authorizeConfig
            .requestMatchers("/users").permitAll()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/swagger-ui/**").permitAll()
            .anyRequest().authenticated();
        }
      )
      .csrf(csrf -> csrf.disable())
      .cors(cors -> cors.disable())
      .addFilter(new JwtAuthenticationFilter(authenticationManager))
      .addFilterBefore(new JwtAuthorization(authenticationManager), UsernamePasswordAuthenticationFilter.class)
      .build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    String jwkSetUri = "http://localhost:8080/.well-known/jwks.json"; // Ajuste o URI conforme necessário
    return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
  }
}