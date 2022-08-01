package com.tweetapp.dto;

import lombok.Data;

@Data
public class User {
	private String loginId;

	private String firstName;
	
	private String lastName;
	
	private String emailId;	
	
	private String username;
	
	private String contactNumber;

	public User(String loginId, String firstName, String lastName, String emailId, String username,
			String contactNumber) {
		super();
		this.loginId = loginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.username = username;
		this.contactNumber = contactNumber;
	}
	
}
