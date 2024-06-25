package com.github.juancassiano.taskies.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInputDTO {
  @NotNull(message = "{nome.obrigatorio}")
  private String name;
}
