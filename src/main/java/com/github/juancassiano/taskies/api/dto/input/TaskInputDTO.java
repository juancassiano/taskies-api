package com.github.juancassiano.taskies.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInputDTO {
  private String name; 
  private String description;
  private Long categoryId;
}
