package com.github.juancassiano.taskies.infra.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserCredentials implements UserDetails{

  private String email;
  private String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }
  
}
