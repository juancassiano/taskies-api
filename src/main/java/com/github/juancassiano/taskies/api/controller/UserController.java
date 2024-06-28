package com.github.juancassiano.taskies.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.github.juancassiano.taskies.domain.entity.UserEntity;
import com.github.juancassiano.taskies.domain.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;
  
    public UserController(UserService userService) {
      this.userService = userService;
    }
  
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createUser(@RequestBody UserEntity user){
      return userService.createUser(user);
    }
    
    @GetMapping("/{id}")
    public UserEntity findUser(@PathVariable String email){
      return userService.findUser(email);
    }
  
}
