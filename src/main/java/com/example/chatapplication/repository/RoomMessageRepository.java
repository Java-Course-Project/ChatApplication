package com.example.chatapplication.repository;

import com.example.chatapplication.document.RoomMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomMessageRepository extends MongoRepository<RoomMessage, String>, SpecificationQuery<RoomMessage> {
}
