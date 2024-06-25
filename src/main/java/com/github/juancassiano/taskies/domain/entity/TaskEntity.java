package com.github.juancassiano.taskies.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;

    private LocalDate created_at;

    private LocalDate updated_at;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    private Boolean done = Boolean.FALSE;

    @PrePersist
    private void onCreate(){
      this.created_at = LocalDate.now();
      this.updated_at = LocalDate.now();
    }

    @PreUpdate
    private void onUpdate(){
      this.updated_at = LocalDate.now();
    }

    public void isDone(){
      this.done = Boolean.TRUE;
    }
    public void isNotDone(){
      this.done = Boolean.FALSE;
    }
}
