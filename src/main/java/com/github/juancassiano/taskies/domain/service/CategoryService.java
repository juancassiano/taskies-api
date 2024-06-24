package com.github.juancassiano.taskies.domain.service;

import java.util.List;

import com.github.juancassiano.taskies.domain.entity.CategoryEntity;
import com.github.juancassiano.taskies.domain.exception.CategoryNotFoundException;
import com.github.juancassiano.taskies.domain.repository.CategoryRepository;

import jakarta.transaction.Transactional;

public class CategoryService{
  
  private CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Transactional
  public CategoryEntity createCategory(CategoryEntity category) {
    return categoryRepository.save(category);
  }

  @Transactional
  public CategoryEntity updateCategory(CategoryEntity category) {
    CategoryEntity updatedCategory = findCategoryById(category.getId());
    return categoryRepository.save(updatedCategory);
  }
  
    @Transactional
    public void deleteCategoryById(Long id) {
        CategoryEntity deletedCategory = findCategoryById(id);
        categoryRepository.delete(deletedCategory);
    }

  public List<CategoryEntity> findAllCategories() {
    return categoryRepository.findAll();
  }

  public CategoryEntity findCategoryById(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
  }
  public CategoryEntity findCategoryByName(String name) {
    return categoryRepository.findByName(name).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
  }

}
