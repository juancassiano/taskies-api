package com.github.juancassiano.taskies.domain.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.juancassiano.taskies.api.dto.input.UserLoginInputDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    public boolean isLoginCorrect(UserLoginInputDTO userLoginInputDTO, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(userLoginInputDTO.getPassword(), this.password);
    }
}
