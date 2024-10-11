package com.example.chatapplication.mapper;

import com.example.chatapplication.document.User;
import com.example.chatapplication.dto.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserRequestToUserMapper extends com.example.chatapplication.mapper.Mapper<UserRequest, User> {
	UserRequestToUserMapper INSTANCE = Mappers.getMapper(UserRequestToUserMapper.class);
	
	@Override
	User map(UserRequest source);
}