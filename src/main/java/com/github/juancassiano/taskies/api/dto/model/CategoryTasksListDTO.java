package com.github.juancassiano.taskies.api.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryTasksListDTO {

  private Long id;
  private String name;
  private String description;
  private boolean done;
}

