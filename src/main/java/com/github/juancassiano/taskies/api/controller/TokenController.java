package com.github.juancassiano.taskies.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import com.github.juancassiano.taskies.api.dto.input.UserInputDTO;
import com.github.juancassiano.taskies.api.dto.model.UserDTO;
import com.github.juancassiano.taskies.api.mapper.UserMapper;
import com.github.juancassiano.taskies.domain.entity.UserEntity;
import com.github.juancassiano.taskies.domain.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.time.Instant;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/login")
public class TokenController {

  private final JwtEncoder jwtEncoder;
  private final UserService userService;
  private final BCryptPasswordEncoder passwordEncoder;
  private UserMapper userMapper;

  public TokenController(JwtEncoder jwtEncoder, UserService userService,BCryptPasswordEncoder passwordEncoder,UserMapper userMapper) {
    this.jwtEncoder = jwtEncoder;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  @PostMapping("")
  public ResponseEntity<UserDTO> login(@RequestBody @Valid UserInputDTO userInputDTO) {
    UserEntity user = userService.findByUsername(userInputDTO.getUsername());

    if (!user.isLoginCorrect(userInputDTO, passwordEncoder)) {
      throw new BadCredentialsException("User or password is invalid");
    }

    Long expiresIn = 300L; //5min
    Instant now = Instant.now();

    JwtClaimsSet claims = JwtClaimsSet.builder()
      .issuer("todo-taskies-api")
      .subject(user.getId().toString(0))
      .expiresAt(now.plusSeconds(expiresIn))
      .issuedAt(now)
      .build();

      String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
      
      UserDTO userDTO = userMapper.toUserDTO(user);
      UserDTO.builder().accessToken(jwtValue).expiresIn(expiresIn).build();

      return ResponseEntity.ok(userDTO);
    }  
}
