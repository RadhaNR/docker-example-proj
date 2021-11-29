package com.example.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.users.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
