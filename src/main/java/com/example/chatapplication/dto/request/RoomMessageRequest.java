package com.example.chatapplication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomMessageRequest {
	@JsonProperty("room_id")
	@Size(max = 128)
	private String roomId;

	@Size(max = 1024)
	private String content;
}
