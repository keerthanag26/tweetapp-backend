package com.tweetapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweetapp.model.UserRegistration;
import com.tweetapp.repo.RegistrationRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	RegistrationRepository registrationRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserRegistration userRegistration = registrationRepository.findByUsername(username);
		
		
		return new User(userRegistration.getUsername(), userRegistration.getPassword(), new ArrayList<>());
	}

	
}
