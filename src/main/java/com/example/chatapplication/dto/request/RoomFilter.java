package com.example.chatapplication.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomFilter {
	@Size(max = 124)
	private String name;
	@Size(max = 124)
	private String belongTo;
}
