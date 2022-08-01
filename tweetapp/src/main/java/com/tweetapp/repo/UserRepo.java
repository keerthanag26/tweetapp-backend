package com.tweetapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.dto.User;

public interface UserRepo extends MongoRepository<User, String> {

	User findByUsername(String username);

}
