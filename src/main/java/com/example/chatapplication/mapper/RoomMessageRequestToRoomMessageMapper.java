package com.example.chatapplication.mapper;

import com.example.chatapplication.document.RoomMessage;
import com.example.chatapplication.dto.request.RoomMessageRequest;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface RoomMessageRequestToRoomMessageMapper extends Mapper<RoomMessageRequest, RoomMessage> {
	RoomMessageRequestToRoomMessageMapper INSTANCE = Mappers.getMapper(RoomMessageRequestToRoomMessageMapper.class);

	RoomMessage map(RoomMessageRequest roomMessageRequest, String roomId, String senderId);
}
