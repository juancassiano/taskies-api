package com.github.juancassiano.taskies.domain.exception;

public class UserAlreadyExistsException extends RuntimeException{
  public UserAlreadyExistsException(String email) {
    super(String.format("Já existe um usuário com o email %s", email));
  }
}
