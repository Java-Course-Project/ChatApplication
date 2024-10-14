package com.example.chatapplication.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomMessageRequest {
	@Size(max = 1024)
	private String content;
}
