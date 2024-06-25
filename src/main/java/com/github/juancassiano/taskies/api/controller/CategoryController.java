package com.github.juancassiano.taskies.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.github.juancassiano.taskies.api.dto.CategoryDTO;
import com.github.juancassiano.taskies.api.dto.input.CategoryInputDTO;
import com.github.juancassiano.taskies.api.mapper.CategoryMapper;
import com.github.juancassiano.taskies.domain.entity.CategoryEntity;
import com.github.juancassiano.taskies.domain.service.CategoryService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/categories")
public class CategoryController {

  private CategoryService categoryService;
  private CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }


    @GetMapping()
    public List<CategoryDTO> getAllCategory() {
        List<CategoryEntity> tasks = categoryService.findAll();
        return categoryMapper.toCategoryDTOList(tasks);
    }

    @GetMapping("/{id}")
    public CategoryDTO findCategoryById(@PathVariable Long id) {
        CategoryEntity category = categoryService.findCategoryById(id);
        return categoryMapper.toCategoryDTO(category);
    }
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody CategoryInputDTO categoryInputDTO) {
        CategoryEntity category = categoryMapper.toDomainCategory(categoryInputDTO);
        category = categoryService.createCategory(category);
        return categoryMapper.toCategoryDTO(category);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryInputDTO categoryInputDTO) {
        CategoryEntity categoryAtual = categoryService.findCategoryById(id);
        categoryMapper.copyToDomainCategory(categoryInputDTO, categoryAtual);
        categoryAtual = categoryService.updateCategory(categoryAtual);
        return categoryMapper.toCategoryDTO(categoryAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
      categoryService.deleteCategoryById(id);  
    } 
}