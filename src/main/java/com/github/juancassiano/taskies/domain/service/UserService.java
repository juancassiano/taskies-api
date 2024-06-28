package com.github.juancassiano.taskies.domain.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.juancassiano.taskies.domain.entity.UserEntity;
import com.github.juancassiano.taskies.domain.exception.EntityInUseException;
import com.github.juancassiano.taskies.domain.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
  
  private static final String USER_ALREADY_EXISTS = "User with Email already Exists";
  private UserRepository userRepository;


  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity createUser(UserEntity user) {
    userRepository.findByEmail(user.getEmail()).ifPresent(userEntity -> {
      throw new EntityInUseException(String.format(USER_ALREADY_EXISTS, user.getEmail()));
    });
    user.setPassword(encodePassword(user.getPassword()));
    System.out.println("User: " + user.getEmail() + " " + user.getPassword());
    return userRepository.save(user);
  }

  public UserEntity findUser(String email) {
    return userRepository.findByEmail(email)
    .orElseThrow(() -> new EntityNotFoundException("User not found"));
  }


  private String encodePassword(String password) {
    if (password == null || password.isEmpty()) {
      throw new IllegalArgumentException("Password cannot be null");
    }
    String passwordEncoded = new BCryptPasswordEncoder().encode(password);
    return passwordEncoded;
  }
}
