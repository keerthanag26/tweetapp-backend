package com.tweetapp.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class UserRegistration {
	
	@Id
	@Indexed(unique = true)
	@NotNull(message = "Login id should be unique")
	private String loginId;
	
	@NotNull(message = "FirstName must be mandatory")
	private String firstName;
	
	@NotNull(message = "LastName must be mandatory")
	private String lastName;
	
	@NotNull
	@Indexed(unique = true)
	@Email(message = "Email should be unique")
	private String emailId;
	
	@NotNull(message = "UserName must be mandatory")
	private String username;
	
	@NotNull(message = "Password must be mandatory")
	private String password;
	
	@NotNull(message = "ConfirmPassword must be mandatory")
	private String confirmPassword;
	
	@NotNull
	private String contactNumber;

}
