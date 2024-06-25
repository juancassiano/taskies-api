package com.github.juancassiano.taskies.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInputDTO {
  @NotBlank(message = "{nome.obrigatorio}")
  private String name;
}
