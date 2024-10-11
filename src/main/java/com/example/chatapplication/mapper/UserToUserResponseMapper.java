package com.example.chatapplication.mapper;

import com.example.chatapplication.document.User;
import com.example.chatapplication.dto.response.UserResponse;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface UserToUserResponseMapper extends Mapper<User, UserResponse> {
	UserToUserResponseMapper INSTANCE = Mappers.getMapper(UserToUserResponseMapper.class);

	@Override
	UserResponse map(User source);
}
