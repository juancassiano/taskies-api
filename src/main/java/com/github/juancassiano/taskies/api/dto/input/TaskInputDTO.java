package com.github.juancassiano.taskies.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInputDTO {
  @NotNull(message = "{nome.obrigatorio}")
  private String name; 
  private String description;
  @NotNull(message = "{categoriaId.obrigatorio}")
  private Long categoryId;
}
