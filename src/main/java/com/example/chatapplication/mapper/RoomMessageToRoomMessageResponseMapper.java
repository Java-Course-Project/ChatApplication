package com.example.chatapplication.mapper;

import com.example.chatapplication.document.RoomMessage;
import com.example.chatapplication.dto.response.RoomMessageResponse;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface RoomMessageToRoomMessageResponseMapper extends Mapper<RoomMessage, RoomMessageResponse> {
	RoomMessageToRoomMessageResponseMapper INSTANCE = Mappers.getMapper(RoomMessageToRoomMessageResponseMapper.class);
}
