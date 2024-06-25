package com.github.juancassiano.taskies.domain.exception;

public class EntityInUseException extends RuntimeException {

  public EntityInUseException(String message){
    super(message);
  }
  
}
