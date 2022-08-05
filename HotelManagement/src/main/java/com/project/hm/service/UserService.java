package com.project.hm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.hm.customexceptions.RegistrationCustomException;
import com.project.hm.customexceptions.UserNotValidException;

import com.project.hm.entity.Authorities;
import com.project.hm.entity.LoginRequest;
import com.project.hm.entity.UserRegistration;
import com.project.hm.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/*@Autowired
	AuthenticationManager authenticationManager;*/
	public Object saveUser(UserRegistration userRegistration) {
		
		return userRepository.save(userRegistration);
	}

	public List<UserRegistration> findAll() {
		return userRepository.findAll();
	}
	/*public LoginRequest login(LoginRequest loginRequest) throws Exception {
		
		try {
			UsernamePasswordAuthenticationToken authenticationProvider= new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
			authenticationManager.authenticate(authenticationProvider);
		}
		catch (Exception e) {
			e.printStackTrace();
//		throw new Exception("Invalid Username or password");
		}
		
		
		return loginRequest;*/
	
public UserRegistration addReg(UserRegistration registration) {

	
		
		
		if (registration == null) {
			throw new RuntimeException("null found in registration plss check");
		} else if (userRepository.existsByUsername(registration.getUsername())) {
			throw new RegistrationCustomException("707", "Username Already Exists please enter unique");
		} else

		{
			System.out.println("AddReg executed>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

			registration.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
			Authorities authority = new Authorities();
			authority.setRole("USER");
			List<Authorities> authorities = new ArrayList<Authorities>();
			authorities.add(authority);
			registration.setAuthorities(authorities);
			userRepository.save(registration);
			return registration;
		}

	}
}
