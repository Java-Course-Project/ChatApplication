package com.example.chatapplication.controller;

import com.example.chatapplication.dto.request.RoomFilter;
import com.example.chatapplication.dto.request.RoomRequest;
import com.example.chatapplication.dto.response.PageResponse;
import com.example.chatapplication.dto.response.RoomResponse;
import com.example.chatapplication.service.RoomService;
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
public class RoomController {
	private final RoomService roomService;

	@GetMapping("/rooms")
	public ResponseEntity<PageResponse<RoomResponse>> findAll(RoomFilter filter, Pageable pageable) {
		return ResponseEntity.ok(roomService.findAll(filter, pageable));
	}

	@GetMapping("/rooms/{room-id}")
	public ResponseEntity<RoomResponse> findById(@PathVariable(name = "room-id") String roomId) {
		return ResponseEntity.ok(roomService.findById(roomId));
	}

	@PostMapping("/users/{user-id}/rooms")
	public ResponseEntity<String> save(@PathVariable(name = "user-id") String creatorId, @Valid @RequestBody RoomRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(roomService.save(creatorId, request));
	}

	@PostMapping("/users/{user-id}/rooms/{room-id}/join")
	public ResponseEntity<Void> join(@PathVariable(name = "user-id") String userId, @PathVariable(name = "room-id") String roomId) {
		roomService.join(userId, roomId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/users/{user-id}/rooms/{room-id}/leave")
	public ResponseEntity<Void> leave(@PathVariable(name = "user-id") String userId, @PathVariable(name = "room-id") String roomId) {
		roomService.leave(userId, roomId);
		return ResponseEntity.noContent().build();
	}
}
