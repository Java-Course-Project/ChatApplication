package com.example.chatapplication.mapper;

import com.example.chatapplication.document.Room;
import com.example.chatapplication.dto.request.RoomRequest;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING, imports = {List.class, ArrayList.class})
public interface RoomRequestToRoomMapper extends Mapper<RoomRequest, Room> {
	RoomRequestToRoomMapper INSTANCE = Mappers.getMapper(RoomRequestToRoomMapper.class);

	@Mapping(target = "participantIds", expression = "java(new ArrayList<>(List.of(participantId)))")
	Room map(RoomRequest request, String participantId);
}
