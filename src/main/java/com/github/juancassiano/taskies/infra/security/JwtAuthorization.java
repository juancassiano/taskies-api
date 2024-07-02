package com.github.juancassiano.taskies.infra.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorization extends BasicAuthenticationFilter{

  public JwtAuthorization(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  private static final String TOKEN_PREFIX = "Bearer ";
  private static final String SECRET = "secret"; 

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, java.io.IOException {
    String header = request.getHeader("Authorization");
    if(header == null || !header.startsWith(TOKEN_PREFIX)){
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
  }

  public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
    String token = request.getHeader("Authorization");
    if(token != null){
      String user = Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
        .getBody()
        .getSubject();
      if(user != null){
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
      }
      return null;
    }
    return null;
  }
  
}
