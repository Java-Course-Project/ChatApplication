package com.example.chatapplication.document;

import java.time.Instant;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "rooms")
@Data
@NoArgsConstructor
public class Room {
	@Id
	private String id;

	@Indexed(unique = true)
	private String name;

	@Field("participant_ids")
	private Set<String> participantIds;

	@Field("created_at")
	@CreatedDate
	private Instant createdAt;

	@Field("created_by")
	@CreatedBy
	private String createdBy;
}
