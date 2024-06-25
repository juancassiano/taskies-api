package com.github.juancassiano.taskies.domain.exception;

public class TaskNotFoundException extends RuntimeException{
  public TaskNotFoundException(String message) {
    super(message);
  }
  public TaskNotFoundException(Long taskId) {
    super(String.format("Não existe task com o ID %d", taskId));
  }
}
