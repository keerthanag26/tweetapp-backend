package com.tweetapp.dto;

public class TweetLoginResponse {

	private final String jwt;

	public TweetLoginResponse(String jwt) {
		this.jwt = jwt;
	}
	public String getJwt() {
		return jwt;
	}
}
