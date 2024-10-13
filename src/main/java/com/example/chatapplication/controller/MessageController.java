package com.example.chatapplication.controller;

import com.example.chatapplication.dto.request.MessageFilter;
import com.example.chatapplication.dto.request.MessageRequest;
import com.example.chatapplication.dto.response.MessageResponse;
import com.example.chatapplication.dto.response.PageResponse;
import com.example.chatapplication.service.MessageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MessageController {
	private final MessageService messageService;

	@GetMapping("/users/{user-id}/messages")
	public ResponseEntity<PageResponse<MessageResponse>> findAllBelongToUser(@PathVariable("user-id") @Valid @Size(max = 100) String userId,
																			 MessageFilter filter,
																			 Pageable pageable) {
		return ResponseEntity.ok(messageService.findAllBelongToUser(userId, filter, pageable));
	}

	@GetMapping("/messages/{message-id}")
	public ResponseEntity<MessageResponse> findById(@PathVariable("message-id") @Valid @Size(max = 100) String messageId) {
		return ResponseEntity.ok(messageService.findById(messageId));
	}

	@PostMapping("/users/{user-id}/messages")
	public ResponseEntity<String> save(@PathVariable("user-id") String senderId, @RequestBody @Valid MessageRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(messageService.save(senderId, request));
	}

}
