package com.tweetapp.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.model.Tweet;

public interface TweetRepo extends MongoRepository<Tweet, Integer> {

	List<Tweet> findByUsername(String username);

	//Tweet findByTweetIdAndUsername(String username, String tweetId);

	Tweet findByTweetId(Integer tweetId);

	Tweet getTweetByUsernameAndTweetId(String username, Integer id);

	//Tweet getUserByUserUsernameAndTweetId(String username, Integer id);


}
