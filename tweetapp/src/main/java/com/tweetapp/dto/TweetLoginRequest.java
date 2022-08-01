package com.tweetapp.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TweetLoginRequest {

	@NotNull(message = "USername is mandatory")
	private String username;
	@NotNull(message = "Password is mandatory")
	private String password;
	public TweetLoginRequest(@NotNull(message = "USername is mandatory") String username,
			@NotNull(message = "Password is mandatory") String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
}
