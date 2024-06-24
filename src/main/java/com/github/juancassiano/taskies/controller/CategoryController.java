package com.github.juancassiano.taskies.controller;

import org.springframework.web.bind.annotation.RestController;

import com.github.juancassiano.taskies.domain.entity.CategoryEntity;
import com.github.juancassiano.taskies.domain.service.CategoryService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/category")
public class CategoryController {

  private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping()
    public List<CategoryEntity> getAllCategory() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryEntity findCategoryById(@PathVariable Long categoryId) {
        CategoryEntity category = categoryService.findCategoryById(categoryId);
        return category;
    }
    
    @PostMapping()
    public CategoryEntity createCategory(@RequestBody CategoryEntity entity) {
        return categoryService.createCategory(entity);
    }

    @PutMapping("/{id}")
    public CategoryEntity updateCategory(@PathVariable Long id, @RequestBody CategoryEntity categoryEntity) {
        return categoryService.updateCategory(categoryEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long categoryId) {
      categoryService.deleteCategoryById(categoryId);  
    }
    
    
  
}
