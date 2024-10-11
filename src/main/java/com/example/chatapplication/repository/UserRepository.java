package com.example.chatapplication.repository;

import com.example.chatapplication.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, SpecificationQuery<User> {
}
