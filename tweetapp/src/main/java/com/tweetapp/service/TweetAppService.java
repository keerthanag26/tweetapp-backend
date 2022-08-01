package com.tweetapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.TweetReplyRequest;
import com.tweetapp.dto.TweetUpdateRequest;
import com.tweetapp.exception.TweetAlreadyExistsException;
import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.model.Tweet;
import com.tweetapp.repo.TweetRepo;

@Service
public class TweetAppService {

	private Logger logger = LoggerFactory.getLogger(TweetAppService.class);
	@Autowired
	TweetRepo tweetRepo;

	@Autowired
	SequenceIdService sequenceIdService;

	public String addTweet(String username, Tweet tweet) {
		if (!tweetRepo.existsById(tweet.getTweetId())) {
			tweetRepo.save(tweet);
			logger.info("Tweet added " + tweet);
		} else {
			throw new TweetAlreadyExistsException("Tweet already exists in tweetId " + tweet.getTweetId());
		}
		return "Tweet added Successfully";
	}

	public Tweet updateTweet(TweetUpdateRequest tweetUpdateRequest) {
		logger.info("Tweet update started");
		Tweet tweet = tweetRepo.getTweetByUsernameAndTweetId(tweetUpdateRequest.getUsername(),
				tweetUpdateRequest.getTweetId());
		// logger.info("Tweet update"+tweet);
		tweet.setTweet(tweetUpdateRequest.getTweet());
		tweetRepo.save(tweet);
		logger.info("Tweet updated " + tweet);
		return tweet;
	}

	public List<Tweet> getAllTweet() {
		List<Tweet> tweets = tweetRepo.findAll();
		if (tweets != null) {
			logger.info("All tweets fetched");
			return tweets;
		} else {
			logger.error("No tweets stored in db");
			throw new TweetNotFoundException("No tweets found added by user");
		}

	}

	public List<Tweet> getAllTweetsByUsername(String username) {
		List<Tweet> tweets = tweetRepo.findByUsername(username);
		if (tweets != null) {
			logger.info("Tweets fetched based on username " + username + " tweets are " + tweets);
			return tweets;
		} else {
			throw new TweetNotFoundException("No tweets found. User not added any tweet yet");
		}
	}

	public Tweet deleteTweet(String username, Integer id) {
		Tweet tweet = tweetRepo.getTweetByUsernameAndTweetId(username, id);
		if (tweet != null) {
			tweetRepo.delete(tweet);
			logger.info("Tweet deleted ");
			return tweet;
		} else {
			throw new TweetNotFoundException("Tweet not found for tweetId " + id);
		}
	}

	public Tweet likeTweet(String username, Integer id) {
		Tweet tweet = tweetRepo.findByTweetId(id);
		if (tweet != null) {
			tweet.setLike(tweet.getLike() + 1);
			tweetRepo.save(tweet);
			logger.info("Tweet liked by " + username);
			return tweet;
		} else {
			throw new TweetNotFoundException("Tweet not found for tweetId " + id);
		}
	}

	public Tweet replyTweet(String username, TweetReplyRequest tweetReplayRequest) {
		Tweet tweet = tweetRepo.findByTweetId(tweetReplayRequest.getTweetId());
		List<String> tweetReply = tweet.getTweetReply();
		tweetReply.add(tweetReplayRequest.getTweetReply());
		tweet.setTweetReply(tweetReply);
		tweetRepo.save(tweet);
		logger.info("Tweet Replyed by " + username);
		return tweet;
	}

	public Tweet getTweetByTweetId(Integer tweetId) {
		Tweet tweet = tweetRepo.findByTweetId(tweetId);
		if (tweet != null) {
			logger.info("Tweet retried based on tweet id " + tweetId + " " + tweet);
			return tweet;
		} else {
			throw new TweetNotFoundException("Tweet not found for tweetId " + tweetId);
		}
	}

}
