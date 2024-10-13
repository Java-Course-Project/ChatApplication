package com.example.chatapplication.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;
import lombok.Data;

@Data
public class RoomResponse {
	private String id;
	private String name;
	private List<String> participants;
	@JsonProperty("created_at")
	private Instant createdAt;
	@JsonProperty("created_by")
	private String createdBy;
}
