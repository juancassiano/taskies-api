package com.github.juancassiano.taskies.domain.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.juancassiano.taskies.domain.entity.UserEntity;
import com.github.juancassiano.taskies.domain.exception.UserAlreadyExistsException;
import com.github.juancassiano.taskies.domain.exception.UsernameNotFoundException;
import com.github.juancassiano.taskies.domain.repository.UserRepository;

import java.util.List; // Add this import statement

import jakarta.transaction.Transactional;

@Service
public class UserService {

  private UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }
  
  public UserEntity findByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
  }
  public void checkIfUserExistByEmail(String email) {
    if (userRepository.findByEmail(email).isPresent()) throw new UserAlreadyExistsException(email);
  }
  
  @Transactional
  public UserEntity createUser(UserEntity user) {
    if(userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new UserAlreadyExistsException(user.getUsername());
    }
    checkIfUserExistByEmail(user.getEmail());

    encodePassword(user);
    return userRepository.save(user);
  }

  private void encodePassword(UserEntity user) {
    if (user.getPassword() == null) {
      throw new IllegalArgumentException("Password cannot be null");
    }
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
  }
}
