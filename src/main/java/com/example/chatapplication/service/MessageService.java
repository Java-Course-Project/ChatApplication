package com.example.chatapplication.service;

import com.example.chatapplication.document.Message;
import com.example.chatapplication.dto.request.MessageFilter;
import com.example.chatapplication.dto.request.MessageRequest;
import com.example.chatapplication.dto.response.MessageResponse;
import com.example.chatapplication.dto.response.PageResponse;
import com.example.chatapplication.exception.ResourceNotFoundException;
import com.example.chatapplication.mapper.MessageRequestToMessageMapper;
import com.example.chatapplication.mapper.MessageToMessageResponseMapper;
import com.example.chatapplication.repository.MessageRepository;
import com.example.chatapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static com.example.chatapplication.utils.CriteriaBuilder.stringContains;
import static com.example.chatapplication.utils.CriteriaBuilder.stringEquals;
import static com.example.chatapplication.utils.CriteriaBuilder.timeBetween;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final MessageRepository messageRepository;
	private final UserRepository userRepository;

	public String save(String senderId, MessageRequest request) {
		if (!userRepository.existsById(senderId) || !userRepository.existsById(request.getReceiverId())) {
			throw new ResourceNotFoundException("User with id " + senderId + " or receiver id " + request.getReceiverId() + " not found");
		}

		Message message = MessageRequestToMessageMapper.INSTANCE.map(request, senderId);
		return messageRepository.save(message).getId();
	}

	public PageResponse<MessageResponse> findAllBelongToUser(String userId, MessageFilter filter, Pageable pageable) {
		return MessageToMessageResponseMapper.INSTANCE.map(
				messageRepository.findByCriteria(
						new Query(stringEquals("senderId", userId).andOperator(stringContains("content", filter.getContent()),
																			 timeBetween("createdAt", filter.getFrom(), filter.getTo())))
						, pageable, Message.class));
	}

	public MessageResponse findById(String id) {
		return MessageToMessageResponseMapper.INSTANCE
				.map(messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message with id " + id + " not found")));
	}
}
