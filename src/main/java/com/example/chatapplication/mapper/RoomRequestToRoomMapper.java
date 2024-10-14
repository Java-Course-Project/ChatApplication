package com.example.chatapplication.mapper;

import com.example.chatapplication.document.Room;
import com.example.chatapplication.dto.request.RoomRequest;
import java.util.HashSet;
import java.util.Set;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING, imports = {Set.class, HashSet.class})
public interface RoomRequestToRoomMapper extends Mapper<RoomRequest, Room> {
	RoomRequestToRoomMapper INSTANCE = Mappers.getMapper(RoomRequestToRoomMapper.class);

	@Mapping(target = "participantIds", expression = "java(new HashSet<>(Set.of(participantId)))")
	Room map(RoomRequest request, String participantId);
}
