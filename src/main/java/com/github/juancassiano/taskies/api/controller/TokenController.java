package com.github.juancassiano.taskies.api.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import com.github.juancassiano.taskies.api.dto.input.UserLoginInputDTO;
import com.github.juancassiano.taskies.api.dto.model.UserLoginDTO;
import com.github.juancassiano.taskies.domain.entity.UserEntity;
import com.github.juancassiano.taskies.domain.service.UserService;

import java.time.Instant;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/login")
public class TokenController {

  private final JwtEncoder jwtEncoder;
  private final UserService userService;
  private final BCryptPasswordEncoder passwordEncoder;

  public TokenController(JwtEncoder jwtEncoder, UserService userService,BCryptPasswordEncoder passwordEncoder) {
    this.jwtEncoder = jwtEncoder;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("")
  public UserLoginDTO login(@RequestBody @Valid UserLoginInputDTO userLoginInputDTO) {
    UserEntity user = userService.findByUsername(userLoginInputDTO.getUsername());

    if (!user.isLoginCorrect(userLoginInputDTO, passwordEncoder)) {
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
      
   
      return new UserLoginDTO(jwtValue, expiresIn);
    }  
}
