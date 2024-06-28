package com.github.juancassiano.taskies.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.github.juancassiano.taskies.domain.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
  Optional<UserEntity> findByEmail(String email);
}
