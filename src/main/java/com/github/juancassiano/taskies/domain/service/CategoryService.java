package com.github.juancassiano.taskies.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.juancassiano.taskies.domain.entity.CategoryEntity;
import com.github.juancassiano.taskies.domain.exception.CategoryNotFoundException;
import com.github.juancassiano.taskies.domain.exception.EntityInUseException;
import com.github.juancassiano.taskies.domain.exception.TaskNotFoundException;
import com.github.juancassiano.taskies.domain.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService{
  String MSG_CATEGORY_IN_USE = "Task com ID %d não pode ser removida pois está em uso";
  String CATEGORY_ALREADY_EXISTS = "Category with name %s already exists";
  
  private CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Transactional
  public CategoryEntity createCategory(CategoryEntity category) {
    categoryRepository.findByName(category.getName()).ifPresent(categoryEntity -> {
      throw new EntityInUseException(String.format(CATEGORY_ALREADY_EXISTS, category.getName()));
    });
    return categoryRepository.save(category);
  }

  @Transactional
  public CategoryEntity updateCategory(CategoryEntity category) {
    try{
      CategoryEntity updatedCategory = findCategoryById(category.getId());
      updatedCategory.setName(category.getName());
      return categoryRepository.save(updatedCategory);
    }catch(TaskNotFoundException | EmptyResultDataAccessException e){
      throw new CategoryNotFoundException(category.getId());
    }catch(DataIntegrityViolationException e){
      throw new EntityInUseException(String.format(MSG_CATEGORY_IN_USE, category.getId()));
    }
  }
  
  @Transactional
  public void deleteCategoryById(Long id) {
    try{
      categoryRepository.deleteById(id);
      categoryRepository.flush();
    }catch(TaskNotFoundException | EmptyResultDataAccessException e){
      throw new CategoryNotFoundException(id);
    }catch(DataIntegrityViolationException e){
      throw new EntityInUseException(String.format(MSG_CATEGORY_IN_USE, id));
    }
  }

  public List<CategoryEntity> findAll() {
    return categoryRepository.findAll();
  }

  public CategoryEntity findCategoryById(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
  }
  public CategoryEntity findCategoryByName(String name) {
    return categoryRepository.findByName(name).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
  }

}
