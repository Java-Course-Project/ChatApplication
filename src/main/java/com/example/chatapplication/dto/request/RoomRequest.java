package com.example.chatapplication.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomRequest {
	@Size(max = 128)
	private String name;

}
