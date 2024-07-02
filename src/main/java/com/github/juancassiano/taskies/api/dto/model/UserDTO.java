package com.github.juancassiano.taskies.api.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private Long id;
  private String name;
  private String email;
  private String username;
}
