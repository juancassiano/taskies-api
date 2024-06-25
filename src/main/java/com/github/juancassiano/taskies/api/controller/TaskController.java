package com.github.juancassiano.taskies.api.controller;

import org.springframework.web.bind.annotation.*;

import com.github.juancassiano.taskies.domain.entity.TaskEntity;
import com.github.juancassiano.taskies.domain.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public List<TaskEntity> getAllTasks() {
    return taskService.findAllTasks();
  }

  @GetMapping("/{id}")
  public TaskEntity getTaskById(@PathVariable Long id) {
    return taskService.findTaskById(id);
  }

  @PostMapping
  public void createTask(@RequestBody TaskEntity task) {
    taskService.createTask(task);
  }

  @PutMapping("/{id}")
  public TaskEntity updateTask(@PathVariable Long id, @RequestBody TaskEntity task) {
    task.setId(id);
    return taskService.updateTask(task);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
  }
}
