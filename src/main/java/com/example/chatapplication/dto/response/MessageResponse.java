package com.example.chatapplication.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import lombok.Data;

@Data
public class MessageResponse {
	private String id;

	@JsonProperty("sender_id")
	private String senderId;

	@JsonProperty("receive_id")
	private String receiverId;

	private String content;

	@JsonProperty("created_at")
	private Instant createdAt;
}
