package com.example.chatapplication.mapper;

import com.example.chatapplication.document.Room;
import com.example.chatapplication.dto.response.RoomResponse;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface RoomToRoomResponseMapper extends Mapper<Room, RoomResponse> {
	RoomToRoomResponseMapper INSTANCE = Mappers.getMapper(RoomToRoomResponseMapper.class);
}
