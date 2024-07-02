package com.github.juancassiano.taskies.api.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputDTO {
  @NotBlank(message = "{nome.obrigatorio}")
  private String name;
  @Email(message = "{email.invalido}")
  private String email;
  @NotBlank(message = "{senha.obrigatorio}")
  private String password;
  @NotBlank(message = "{username.obrigatorio}")
  private String username;
}
