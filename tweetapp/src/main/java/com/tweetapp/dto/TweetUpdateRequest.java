package com.tweetapp.dto;

import lombok.Data;

@Data
public class TweetUpdateRequest {
	
	private String username;
	private Integer tweetId;
	private String tweet;

}
