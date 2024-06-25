package com.github.juancassiano.taskies.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
  private Long id;
  private String name;
  private String description;
  private boolean done;
  private CategoryDTO category;
}
