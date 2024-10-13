package com.example.chatapplication.repository;

import com.example.chatapplication.document.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String>, SpecificationQuery<Room> {
}
