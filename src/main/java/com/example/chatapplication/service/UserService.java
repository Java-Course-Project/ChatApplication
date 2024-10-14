package com.example.chatapplication.service;

import com.example.chatapplication.document.User;
import com.example.chatapplication.dto.request.UserFilter;
import com.example.chatapplication.dto.request.UserRequest;
import com.example.chatapplication.dto.response.PageResponse;
import com.example.chatapplication.dto.response.UserResponse;
import com.example.chatapplication.exception.ResourceNotFoundException;
import com.example.chatapplication.mapper.UserRequestToUserMapper;
import com.example.chatapplication.mapper.UserToUserResponseMapper;
import com.example.chatapplication.repository.UserRepository;
import com.example.chatapplication.utils.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public String save(UserRequest request) {
		return userRepository.save(UserRequestToUserMapper.INSTANCE.map(request)).getId();
	}

	public PageResponse<UserResponse> findAll(UserFilter filter, Pageable pageable) {
		return UserToUserResponseMapper.INSTANCE.map(userRepository.findByCriteria(
				new Query(CriteriaBuilder.stringContains("username", filter.getUsername())), pageable, User.class));
	}

	public void delete(String id) {
		userRepository.deleteById(id);
	}

	public String update(UserRequest request, String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

		user.setUsername(request.getUsername());
		return userRepository.save(user).getId();
	}

	public UserResponse findById(String id) {
		return UserToUserResponseMapper.INSTANCE.map(
				userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found")));
	}
}
