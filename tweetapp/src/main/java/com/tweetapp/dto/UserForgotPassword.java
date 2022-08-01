package com.tweetapp.dto;

import lombok.Data;

@Data
public class UserForgotPassword {
	
	private String username;
	private String newPassword;
	private String confirmPassword;

}
