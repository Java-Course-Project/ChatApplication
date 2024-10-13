package com.example.chatapplication.document;

import java.time.Instant;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "room-messages")
public class RoomMessage {
	@Id
	private String id;

	@Field(name = "sender_id")
	@Indexed
	private String senderId;

	@Field(name = "room_id")
	@Indexed
	private String roomId;

	@TextIndexed
	private String content;

	@Field("created_at")
	@CreatedDate
	@Indexed(direction = IndexDirection.DESCENDING)
	private Instant createdAt;
}
