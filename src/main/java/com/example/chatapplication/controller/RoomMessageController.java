package com.example.chatapplication.controller;

import com.example.chatapplication.dto.request.RoomMessageFilter;
import com.example.chatapplication.dto.request.RoomMessageRequest;
import com.example.chatapplication.dto.response.PageResponse;
import com.example.chatapplication.dto.response.RoomMessageResponse;
import com.example.chatapplication.service.RoomMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomMessageController {
	private final RoomMessageService roomMessageService;

	@GetMapping("/users/{user-id}/room-messages")
	public ResponseEntity<PageResponse<RoomMessageResponse>> findAll(@PathVariable("user-id") String senderId, RoomMessageFilter filter,
																	 Pageable pageable) {
		return ResponseEntity.ok(roomMessageService.findAllBelongToUser(senderId, filter, pageable));
	}

	@GetMapping("/room-messages/{room-message-id}")
	public ResponseEntity<RoomMessageResponse> findById(@PathVariable("room-message-id") String roomMessageId) {
		return ResponseEntity.ok(roomMessageService.findById(roomMessageId));
	}

	@PostMapping("/users/{user-id}/room-messages")
	public ResponseEntity<String> save(@PathVariable("user-id") String senderId, @Valid @RequestBody RoomMessageRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(roomMessageService.save(senderId, request));
	}
}
