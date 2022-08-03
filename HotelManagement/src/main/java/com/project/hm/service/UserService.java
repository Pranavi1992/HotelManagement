package com.project.hm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hm.entity.UserRegistration;
import com.project.hm.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Object saveUser(UserRegistration userRegistration) {
		
		return userRepository.save(userRegistration);
	}

}
