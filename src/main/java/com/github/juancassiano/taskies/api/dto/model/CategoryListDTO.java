package com.github.juancassiano.taskies.api.dto.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CategoryListDTO {
  private Long id;
  private String name;
  private List<TaskDTO> tasks;
}
