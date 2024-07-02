package com.github.juancassiano.taskies.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.juancassiano.taskies.api.dto.input.UserInputDTO;
import com.github.juancassiano.taskies.api.dto.model.UserDTO;
import com.github.juancassiano.taskies.api.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.github.juancassiano.taskies.domain.entity.UserEntity;
import com.github.juancassiano.taskies.domain.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;


    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody @Valid UserInputDTO userInputDTO) {
      UserEntity user = userMapper.toDomainUser(userInputDTO);
      user = userService.createUser(user);
      return userMapper.toUserDTO(user);
    }
}
