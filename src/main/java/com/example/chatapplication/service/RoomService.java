package com.example.chatapplication.service;

import com.example.chatapplication.document.Room;
import com.example.chatapplication.dto.request.RoomFilter;
import com.example.chatapplication.dto.request.RoomRequest;
import com.example.chatapplication.dto.response.PageResponse;
import com.example.chatapplication.dto.response.RoomResponse;
import com.example.chatapplication.exception.ResourceNotFoundException;
import com.example.chatapplication.mapper.RoomRequestToRoomMapper;
import com.example.chatapplication.mapper.RoomToRoomResponseMapper;
import com.example.chatapplication.repository.RoomRepository;
import com.example.chatapplication.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static com.example.chatapplication.utils.CriteriaBuilder.stringContains;
import static com.example.chatapplication.utils.CriteriaBuilder.stringEquals;

@Service
@RequiredArgsConstructor
public class RoomService {
	private final RoomRepository roomRepository;
	private final UserRepository userRepository;

	public String save(String creatorId, RoomRequest roomRequest) {
		if (!userRepository.existsById(creatorId)) {
			throw new ResourceNotFoundException("User with id " + creatorId + " not found");
		}
		return roomRepository.save(RoomRequestToRoomMapper.INSTANCE.map(roomRequest)).getId();
	}

	public void join(String participantId, String roomId) {
		if (!roomRepository.existsById(roomId) || !userRepository.existsById(participantId)) {
			throw new ResourceNotFoundException("Room with id " + roomId + " not found or user with id " + participantId + " not found");
		}

		Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room with id " + roomId + " not found"));
		room.getParticipantIds().add(participantId);
		roomRepository.save(room);
	}

	public void leave(String participantId, String roomId) {
		if (!roomRepository.existsById(roomId) || !userRepository.existsById(participantId)) {
			throw new ResourceNotFoundException("Room with id " + roomId + " not found or user with id " + participantId + " not found");
		}

		Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room with id " + roomId + " not found"));
		room.getParticipantIds().removeIf(e -> Objects.equals(e, participantId));
		roomRepository.save(room);
	}

	public RoomResponse findById(String roomId) {
		return RoomToRoomResponseMapper.INSTANCE.map(roomRepository.findById(roomId)
																   .orElseThrow(() -> new ResourceNotFoundException("Room not with id " + roomId +
																													"found")));
	}

	public PageResponse<RoomResponse> findAll(RoomFilter filter, Pageable pageable) {
		return RoomToRoomResponseMapper.INSTANCE.map(
				roomRepository.findByCriteria(new Query(stringContains("name", filter.getName())
																.andOperator(stringEquals("createdBy", filter.getBelongTo()))),
											  pageable,
											  Room.class));
	}
}
