package com.project.hm.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
	
}
