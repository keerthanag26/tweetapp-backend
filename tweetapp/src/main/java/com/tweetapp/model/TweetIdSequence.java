package com.tweetapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document(collection = "TweetIdSequence")
@Data
public class TweetIdSequence {
	@Id
	private String id;
	private int seq;

}
