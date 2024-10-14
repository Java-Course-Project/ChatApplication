package com.example.chatapplication.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
	private String error;
	private String message;
	private String path;
}
