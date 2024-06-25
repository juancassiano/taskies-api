package com.github.juancassiano.taskies.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.juancassiano.taskies.api.dto.TaskDTO;
import com.github.juancassiano.taskies.api.dto.input.TaskInputDTO;
import com.github.juancassiano.taskies.domain.entity.TaskEntity;

@Component
public class TaskMapper {
  
  @Autowired
  private ModelMapper modelMapper;

  public TaskEntity toDomainTask(TaskInputDTO taskInputDTO){
    return modelMapper.map(taskInputDTO, TaskEntity.class);
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
