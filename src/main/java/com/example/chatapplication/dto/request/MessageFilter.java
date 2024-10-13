package com.example.chatapplication.dto.request;

import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageFilter {
	@Size(max = 1024)
	private String content;
	private Instant from;
	private Instant to = Instant.now();
}
