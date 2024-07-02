package com.github.juancassiano.taskies.domain.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.juancassiano.taskies.api.dto.input.UserInputDTO;

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

    public boolean isLoginCorrect(UserInputDTO userInputDTO, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(userInputDTO.getPassword(), this.password);
    }
}
