package com.tweetapp.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Tweet {
	@Id
	private Integer tweetId;

	@Size(max = 50, message = "TweetTag is optional")
	private String tweetTag;

	@Size(max = 144, message = "Tweet must be mandatory")
	private String tweet;

	private int like;

	@NotNull(message = "UserName must be mandatory")
	private String username;

	private List<String> tweetReply;
}
