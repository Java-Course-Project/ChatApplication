package com.example.chatapplication.dto.request;

import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.Data;

@Data
public class RoomMessageFilter {
	@Size(max = 1024)
	private String content;
	private Instant from;
	private Instant to = Instant.now();
}
