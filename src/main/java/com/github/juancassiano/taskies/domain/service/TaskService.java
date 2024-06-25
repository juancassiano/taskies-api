package com.github.juancassiano.taskies.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.juancassiano.taskies.domain.entity.TaskEntity;
import com.github.juancassiano.taskies.domain.exception.EntityInUseException;
import com.github.juancassiano.taskies.domain.exception.TaskNotFoundException;
import com.github.juancassiano.taskies.domain.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
  private TaskRepository taskRepository;

  String MSG_TASK_IN_USE = "Task com ID %d não pode ser removida pois está em uso";


  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Transactional
  public void createTask(TaskEntity task) {
    taskRepository.save(task);
  }

  @Transactional
  public TaskEntity updateTask(TaskEntity task) {
    try{
      TaskEntity updatedTask = findTaskById(task.getId());
      updatedTask.setName(task.getName());
      updatedTask.setDone(task.getDone());
      updatedTask.setCategory(task.getCategory());
      updatedTask.setDescription(task.getDescription());
      return taskRepository.save(updatedTask);

    }catch(TaskNotFoundException | EmptyResultDataAccessException e){
      throw new TaskNotFoundException(task.getId());
    }catch(DataIntegrityViolationException e){
      throw new EntityInUseException(String.format(MSG_TASK_IN_USE, task.getId()));
    }
  }

  @Transactional
  public void deleteTask(Long taskId) {
    try{
      taskRepository.deleteById(taskId);
      taskRepository.flush();
    }catch(TaskNotFoundException | EmptyResultDataAccessException e){
      throw new TaskNotFoundException(taskId);
    }catch(DataIntegrityViolationException e){
      throw new EntityInUseException(String.format(MSG_TASK_IN_USE, taskId));
    }
  }

  public TaskEntity findTaskById(Long id) {
    return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
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
