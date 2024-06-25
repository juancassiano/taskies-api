package com.github.juancassiano.taskies.api.controller;

import org.springframework.web.bind.annotation.*;

import com.github.juancassiano.taskies.api.dto.TaskDTO;
import com.github.juancassiano.taskies.api.dto.input.TaskInputDTO;
import com.github.juancassiano.taskies.api.mapper.TaskMapper;
import com.github.juancassiano.taskies.domain.entity.TaskEntity;
import com.github.juancassiano.taskies.domain.service.TaskService;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  private TaskMapper taskMapper;

  public TaskController(TaskService taskService, TaskMapper taskMapper) {
    this.taskService = taskService;
    this.taskMapper = taskMapper;
  }

  //TODO: Implementar filtro Done
  @GetMapping
  public List<TaskDTO> getAllTasks() {
    List<TaskEntity> tasks = taskService.findAllTasks();
    return taskMapper.toTaskDTOList(tasks);
  }

  @GetMapping("/{id}")
  public TaskDTO getTaskById(@PathVariable Long id) {
    TaskEntity taskEntity = taskService.findTaskById(id);
    return taskMapper.toTaskDTO(taskEntity);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TaskDTO createTask(@RequestBody TaskInputDTO taskInputDTO) {
    TaskEntity taskEntity = taskMapper.toDomainTask(taskInputDTO);
    taskEntity = taskService.createTask(taskEntity);
    return taskMapper.toTaskDTO(taskEntity);
  }

  @PutMapping("/{id}")
  public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskInputDTO taskInputDTO) {
    TaskEntity taskAtual = taskService.findTaskById(id);
    taskMapper.copyToDomainTask(taskInputDTO, taskAtual);
    taskAtual = taskService.updateTask(taskAtual);
    return taskMapper.toTaskDTO(taskAtual);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
  }
}
