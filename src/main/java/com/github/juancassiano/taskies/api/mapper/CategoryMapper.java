package com.github.juancassiano.taskies.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.juancassiano.taskies.api.dto.CategoryDTO;
import com.github.juancassiano.taskies.api.dto.input.CategoryInputDTO;
import com.github.juancassiano.taskies.domain.entity.CategoryEntity;

@Component
public class CategoryMapper {

  @Autowired
  private ModelMapper modelMapper;

  public CategoryEntity toDomainCategory(CategoryInputDTO categoryInputDTO){
    return modelMapper.map(categoryInputDTO, CategoryEntity.class);
  }

  public void copyToDomainCategory(CategoryInputDTO categoryInputDTO, CategoryEntity categoryEntity){
    modelMapper.map(categoryEntity, categoryEntity);
  }

  public CategoryDTO toCategoryDTO(CategoryEntity categoryEntity){
    return modelMapper.map(categoryEntity, CategoryDTO.class);
  }

  public List<CategoryDTO> toCategoryDTOList(List<CategoryEntity> categoryEntityList){
    return categoryEntityList.stream()
      .map(this::toCategoryDTO)
      .collect(Collectors.toList());
  }
}
