package com.example.chatapplication.mapper;

import com.example.chatapplication.document.Message;
import com.example.chatapplication.dto.request.MessageRequest;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface MessageRequestToMessageMapper extends Mapper<MessageRequest, Message> {
	MessageRequestToMessageMapper INSTANCE = Mappers.getMapper(MessageRequestToMessageMapper.class);

	@Override
	Message map(MessageRequest source);
}
