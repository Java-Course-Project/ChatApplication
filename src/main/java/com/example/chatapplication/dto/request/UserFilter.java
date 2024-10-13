package com.example.chatapplication.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFilter {
	@Size(max = 100)
	private String username;
}
