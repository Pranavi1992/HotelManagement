package com.project.hm.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.hm.entity.LoginRequest;
import com.project.hm.entity.UserRegistration;
import com.project.hm.service.UserService;

@RestController
public class UserController {

	
	
	
	@Autowired
private UserService userService;
  @PostMapping("/registration")

  public ResponseEntity<UserRegistration> saveUser(@Valid @RequestBody UserRegistration userRegistration)
  {
  return new ResponseEntity(userService.saveUser( userRegistration), HttpStatus.CREATED);

  }
  @GetMapping("/getAllUsers")
  public ResponseEntity<List<UserRegistration>> allUsers(){
		List<UserRegistration> allUsers= userService.findAll();
		return new ResponseEntity<List<UserRegistration>>(allUsers, HttpStatus.ACCEPTED);
	
  }
  @PostMapping("/login")
  public ResponseEntity<LoginRequest>login(@RequestBody LoginRequest loginRequest) throws Exception{
	 
	  return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
  }
	
}
