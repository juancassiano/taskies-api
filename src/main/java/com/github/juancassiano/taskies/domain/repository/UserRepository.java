package com.github.juancassiano.taskies.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.github.juancassiano.taskies.domain.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
  Optional<UserEntity> findByEmail(String email);

  Optional<UserEntity> findByUsername(String username);
}
