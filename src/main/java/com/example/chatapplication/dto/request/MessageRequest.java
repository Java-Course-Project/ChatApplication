package com.example.chatapplication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageRequest {
	@JsonProperty("receiver_id")
	private String receiverId;

	@Size(min = 1, max = 1024)
	private String content;
}
