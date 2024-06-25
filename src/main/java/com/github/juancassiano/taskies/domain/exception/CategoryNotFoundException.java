package com.github.juancassiano.taskies.domain.exception;

public class CategoryNotFoundException extends RuntimeException{
  public CategoryNotFoundException(String message) {
    super(message);
  }
  public CategoryNotFoundException(Long categoryId) {
    super(String.format("Não existe category com o ID %d", categoryId));
  }
  
}
