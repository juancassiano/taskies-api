package com.github.juancassiano.taskies.api.dto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
  private Long id;
  private String name;
  private String email;
  private String username;
  private String accessToken;
  private Long expiresIn;
}
