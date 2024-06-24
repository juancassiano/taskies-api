package com.github.juancassiano.taskies.domain.service;

import java.util.List;

import com.github.juancassiano.taskies.domain.entity.TaskEntity;
import com.github.juancassiano.taskies.domain.exception.TaskNotFoundException;
import com.github.juancassiano.taskies.domain.repository.TaskRepository;

import jakarta.transaction.Transactional;

public class TaskService {
  private TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Transactional
  public void createTask(TaskEntity task) {
    taskRepository.save(task);
  }

  @Transactional
  public void updateTask(TaskEntity task) {
    TaskEntity updatedTask = findTaskById(task.getId());
    taskRepository.save(updatedTask);
  }

  @Transactional
  public void deleteTask(TaskEntity task) {
    TaskEntity deletedTask = findTaskById(task.getId());
    taskRepository.delete(deletedTask);
  }

  public TaskEntity findTaskById(Long id) {
    return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found"));
  }

  public List<TaskEntity> findAllTasks() {
    return taskRepository.findAll();
  }

  public List<TaskEntity> findTasksByDoneTrue() {
    return taskRepository.findByDoneTrue();
  }

  public List<TaskEntity> findTasksByDoneFalse() {
      return taskRepository.findByDoneFalse();
  }
  
}
