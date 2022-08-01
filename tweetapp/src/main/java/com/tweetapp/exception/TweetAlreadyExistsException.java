package com.tweetapp.exception;

public class TweetAlreadyExistsException extends RuntimeException {

	/**
	 * Tweet Already Exists Exception
	 */
	private static final long serialVersionUID = 1L;
	public TweetAlreadyExistsException(String msg) {
		super(msg);	
	}

}
