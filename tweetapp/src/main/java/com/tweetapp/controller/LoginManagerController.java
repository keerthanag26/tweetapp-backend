package com.tweetapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.TweetLoginRequest;
import com.tweetapp.dto.TweetLoginResponse;
import com.tweetapp.dto.UserForgotPassword;
import com.tweetapp.model.UserRegistration;
import com.tweetapp.service.MyUserDetailsService;
import com.tweetapp.service.UserAppService;
import com.tweetapp.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/v1.0/tweets/")
public class LoginManagerController {
	private Logger logger = LoggerFactory.getLogger(LoginManagerController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserAppService userAppService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> authenticate(@RequestBody TweetLoginRequest loginRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
					loginRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new TweetLoginResponse(jwt));
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<UserRegistration> addUser(@RequestBody UserRegistration user){
		ResponseEntity<UserRegistration> responseEntity=null;
		if(user.getPassword().equals(user.getConfirmPassword())) {
		UserRegistration registerUser=userAppService.addUser(user);
		if(null!=registerUser ) {
			responseEntity = new ResponseEntity<>(registerUser, HttpStatus.CREATED);
		}
		}
		else {
			logger.info("User should enter matching password");
		}
		return responseEntity;
	}
	
	@PutMapping(value = "/{username}/forgot")
	public ResponseEntity<String> forgotPassword(@RequestBody UserForgotPassword user){
		ResponseEntity<String> responseEntity = null;
		if(user.getNewPassword().equals(user.getConfirmPassword())) {
			String registerUser=userAppService.updatePassword(user);
			responseEntity = new ResponseEntity<>(registerUser, HttpStatus.CREATED);
		}
		else {
			logger.info("User should enter matching password");
		}
		return responseEntity;
	}
	
	@GetMapping(value="/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("auth ok",HttpStatus.OK);
	}
}
