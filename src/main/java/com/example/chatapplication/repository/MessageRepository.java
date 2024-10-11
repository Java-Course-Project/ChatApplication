package com.example.chatapplication.repository;

import com.example.chatapplication.document.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String>, SpecificationQuery<Message> {
}
