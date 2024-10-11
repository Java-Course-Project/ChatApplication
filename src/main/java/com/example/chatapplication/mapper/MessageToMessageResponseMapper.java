package com.example.chatapplication.mapper;

import com.example.chatapplication.document.Message;
import com.example.chatapplication.dto.response.MessageResponse;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface MessageToMessageResponseMapper extends Mapper<Message, MessageResponse> {
	MessageToMessageResponseMapper INSTANCE = Mappers.getMapper(MessageToMessageResponseMapper.class);

	@Override
	MessageResponse map(Message source);
}

