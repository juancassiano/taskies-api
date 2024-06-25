package com.github.juancassiano.taskies.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInputDTO {
  @NotNull
  private String name; 
  private String description;
  @NotNull
  private Long categoryId;
}
