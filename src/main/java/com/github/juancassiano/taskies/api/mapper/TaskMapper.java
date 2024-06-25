package com.github.juancassiano.taskies.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.juancassiano.taskies.api.dto.TaskDTO;
import com.github.juancassiano.taskies.api.dto.input.TaskInputDTO;
import com.github.juancassiano.taskies.domain.entity.CategoryEntity;
import com.github.juancassiano.taskies.domain.entity.TaskEntity;
import com.github.juancassiano.taskies.domain.exception.CategoryNotFoundException;
import com.github.juancassiano.taskies.domain.repository.CategoryRepository;

@Component
public class TaskMapper {
  
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private CategoryRepository categoryRepository;

  public TaskEntity toDomainTask(TaskInputDTO taskInputDTO){
    TaskEntity taskEntity = modelMapper.map(taskInputDTO, TaskEntity.class);
    Long categoryId = taskInputDTO.getCategoryId();
    CategoryEntity category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    taskEntity.setCategory(category);
    return taskEntity;
  }

  public void copyToDomainTask(TaskInputDTO taskInputDTO, TaskEntity taskEntity){
    modelMapper.map(taskEntity, taskEntity);
  }

  public TaskDTO toTaskDTO(TaskEntity taskEntity){
    return modelMapper.map(taskEntity, TaskDTO.class);
  }

  public List<TaskDTO> toTaskDTOList(List<TaskEntity> taskEntityList){
    return taskEntityList.stream()
      .map(this::toTaskDTO)
      .collect(Collectors.toList());
  }
}
