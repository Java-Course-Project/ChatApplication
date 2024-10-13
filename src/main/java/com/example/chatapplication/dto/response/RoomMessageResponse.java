package com.example.chatapplication.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import lombok.Data;

@Data
public class RoomMessageResponse {
	private String id;
	@JsonProperty("sender_id")
	private String senderId;
	@JsonProperty("room_id")
	private String roomId;
	private String content;
	@JsonProperty("created_at")
	private Instant createdAt;
}
