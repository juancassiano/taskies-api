package com.github.juancassiano.taskies.infra.security;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.Authentication;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

  private final AuthenticationManager authenticationManager;
  private final Long expiration = 86400000L;
  private final String secret = "secret";
  
  public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      UserCredentials userCredentials = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword());
      return authenticationManager.authenticate(authToken);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    String email = ((UserCredentials) authResult.getPrincipal()).getUsername();
    Key key = Keys.hmacShaKeyFor(secret.getBytes());
    String token = Jwts.builder()
      .setSubject(email)
      .setExpiration(new Date(System.currentTimeMillis() + expiration))
      .signWith(key, SignatureAlgorithm.HS512)
      .compact();
    String header = email + " " + token;
    response.addHeader("Authorization", "Bearer " + header);
  }
}
