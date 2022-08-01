package com.tweetapp.dto;

import lombok.Data;

@Data
public class TweetReplyRequest {
	
	private Integer tweetId;
	private String tweetReply;

}
