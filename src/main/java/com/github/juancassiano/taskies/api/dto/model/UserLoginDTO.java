package com.github.juancassiano.taskies.api.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginDTO {
  private String accessToken;
  private Long expiresIn;
}
