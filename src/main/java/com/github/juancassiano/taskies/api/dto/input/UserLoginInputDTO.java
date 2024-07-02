package com.github.juancassiano.taskies.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginInputDTO {
  @NotBlank(message = "{senha.obrigatorio}")
  private String password;
  @NotBlank(message = "{username.obrigatorio}")
  private String username;
}
