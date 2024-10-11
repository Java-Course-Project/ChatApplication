package com.example.chatapplication.service;

import com.example.chatapplication.document.Message;
import com.example.chatapplication.dto.request.MessageRequest;
import com.example.chatapplication.dto.response.MessageResponse;
import com.example.chatapplication.dto.response.PageResponse;
import com.example.chatapplication.exception.ResourceNotFound;
import com.example.chatapplication.mapper.MessageRequestToMessageMapper;
import com.example.chatapplication.mapper.MessageToMessageResponseMapper;
import com.example.chatapplication.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final MessageRepository messageRepository;

	public String save(String senderId, MessageRequest request) {
		Message message = MessageRequestToMessageMapper.INSTANCE.map(request);
		message.setSenderId(senderId);
		return messageRepository.save(message).getId();
	}

	public PageResponse<MessageResponse> findAll(Pageable pageable) {
		return MessageToMessageResponseMapper.INSTANCE.map(messageRepository.findAll(pageable));
	}

	public MessageResponse findById(String id) {
		return MessageToMessageResponseMapper.INSTANCE
				.map(messageRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Message with id " + id + " not found")));
	}
}
