package com.github.juancassiano.taskies.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.juancassiano.taskies.domain.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
  List<TaskEntity> findByDoneTrue();
  List<TaskEntity> findByDoneFalse();
}
