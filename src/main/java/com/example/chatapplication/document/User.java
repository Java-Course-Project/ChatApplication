package com.example.chatapplication.document;

import java.time.Instant;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
@Data
public class User {
	@Id
	private String id;

	@Indexed(unique = true)
	private String username;

	@Field("created_at")
	@CreatedDate
	private Instant createdAt;

	@Field("created_by")
	@Indexed(direction = IndexDirection.ASCENDING)
	@CreatedBy
	private String createdBy;
}