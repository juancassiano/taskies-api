package com.github.juancassiano.taskies.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.juancassiano.taskies.api.dto.input.UserInputDTO;
import com.github.juancassiano.taskies.api.dto.model.UserDTO;
import com.github.juancassiano.taskies.domain.entity.UserEntity;

@Component
public class UserMapper {
  
  @Autowired
  private ModelMapper modelMapper;

  public UserEntity toDomainUser(UserInputDTO userInputDTO){
    return modelMapper.map(userInputDTO, UserEntity.class);
  }

  public void copyToDomainUser(UserInputDTO userInputDTO, UserEntity userEntity){
    modelMapper.map(userInputDTO, userEntity);
  }

  public UserDTO toUserDTO(UserEntity userEntity){
    return modelMapper.map(userEntity, UserDTO.class);
  }

  public List<UserDTO> toUserDTOList(List<UserEntity> userEntityList){
    return userEntityList.stream()
      .map(this::toUserDTO)
      .collect(Collectors.toList());
  }
}
