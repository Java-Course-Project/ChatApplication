package com.example.chatapplication.controller;

import com.example.chatapplication.dto.request.UserFilter;
import com.example.chatapplication.dto.request.UserRequest;
import com.example.chatapplication.dto.response.PageResponse;
import com.example.chatapplication.dto.response.UserResponse;
import com.example.chatapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping
	public ResponseEntity<PageResponse<UserResponse>> findAll(UserFilter filter, Pageable pageable) {
		return ResponseEntity.ok(userService.findAll(filter, pageable));
	}

	@PostMapping
	public ResponseEntity<String> save(@RequestBody @Valid UserRequest userRequest) {
		return ResponseEntity.ok(userService.save(userRequest));
	}

	@PutMapping("/{user-id}")
	public ResponseEntity<String> update(@PathVariable(name = "user-id") String userId, @RequestBody @Valid UserRequest userRequest) {
		return ResponseEntity.ok(userService.update(userRequest, userId));
	}

	@DeleteMapping("/{user-id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "user-id") String userId) {
		userService.delete(userId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{user-id}")
	public ResponseEntity<UserResponse> findById(@PathVariable(name = "user-id") String userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}
}
