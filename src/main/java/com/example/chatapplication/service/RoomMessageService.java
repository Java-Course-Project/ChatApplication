package com.example.chatapplication.service;

import com.example.chatapplication.document.Room;
import com.example.chatapplication.document.RoomMessage;
import com.example.chatapplication.dto.request.RoomMessageFilter;
import com.example.chatapplication.dto.request.RoomMessageRequest;
import com.example.chatapplication.dto.response.PageResponse;
import com.example.chatapplication.dto.response.RoomMessageResponse;
import com.example.chatapplication.exception.ResourceNotFoundException;
import com.example.chatapplication.exception.UserNotInRoomException;
import com.example.chatapplication.mapper.RoomMessageRequestToRoomMessageMapper;
import com.example.chatapplication.mapper.RoomMessageToRoomMessageResponseMapper;
import com.example.chatapplication.repository.RoomMessageRepository;
import com.example.chatapplication.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static com.example.chatapplication.utils.CriteriaBuilder.stringContains;
import static com.example.chatapplication.utils.CriteriaBuilder.stringEquals;
import static com.example.chatapplication.utils.CriteriaBuilder.timeBetween;

@Service
@RequiredArgsConstructor
public class RoomMessageService {
	private final RoomMessageRepository roomMessageRepository;
	private final RoomRepository roomRepository;

	public String save(String senderId, String roomId, RoomMessageRequest request) {
		RoomMessage roomMessage = RoomMessageRequestToRoomMessageMapper.INSTANCE.map(request, roomId, senderId);

		Room room = roomRepository.findById(roomMessage.getRoomId())
								  .orElseThrow(() -> new ResourceNotFoundException(
										  "Room not with id " + roomMessage.getRoomId() + " not found"));
		if (!room.getParticipantIds().contains(senderId)) {
			throw new UserNotInRoomException("User with id " + senderId + " not in room with id " + roomMessage.getRoomId());
		}

		return roomMessageRepository.save(roomMessage).getId();
	}

	public RoomMessageResponse findById(String roomMessageId) {
		return RoomMessageToRoomMessageResponseMapper.INSTANCE.map(roomMessageRepository.findById(roomMessageId).orElseThrow(
				() -> new ResourceNotFoundException("Message with id " + roomMessageId + " not found")));
	}

	public PageResponse<RoomMessageResponse> findAllBelongToUserInRoom(String senderId, String roomId, RoomMessageFilter filter,
																	   Pageable pageable) {
		return RoomMessageToRoomMessageResponseMapper.INSTANCE.map(roomMessageRepository.findByCriteria(
				new Query(stringEquals("senderId", senderId).
								  andOperator(stringContains("content", filter.getContent()),
											  stringEquals("roomId", roomId),
											  timeBetween("createdAt", filter.getFrom(), filter.getTo()))),
				pageable, RoomMessage.class));
	}
}
