package com.example.chatapplication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageRequest {
	@JsonProperty("receiver_id")
	@Size(max = 100)
	private String receiverId;

	@Size(max = 1024)
	private String content;
}
