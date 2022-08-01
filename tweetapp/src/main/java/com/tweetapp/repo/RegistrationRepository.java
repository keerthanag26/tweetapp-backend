package com.tweetapp.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.model.UserRegistration;

public interface RegistrationRepository extends MongoRepository<UserRegistration, Integer>{

	List<UserRegistration> findByUsernameOrEmailId(String username,String emailId);

	UserRegistration findByUsername(String username);

}
