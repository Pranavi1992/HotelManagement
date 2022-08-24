package com.project.hm.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.hm.customexceptions.UserNotValidException;
import com.project.hm.entity.LoginRequest;
import com.project.hm.entity.UserRegistration;
import com.project.hm.jwt.JwtTokenString;
import com.project.hm.jwt.JwtUtil;
import com.project.hm.service.UserService;



@RestController

public class UserController {

	@Autowired
	JwtUtil jwtUtil;


	@Autowired
	AuthenticationManager authenticationManager;

	
	
	@Autowired
private UserService userService;
	
  
	@PostMapping("user/registration")
  public ResponseEntity<UserRegistration> saveUser(@Valid @RequestBody UserRegistration userRegistration)
  {
	  System.out.println("Save User>>>>>>>>>>>>>>>>>>>>>>");
  return new ResponseEntity(userService.addReg( userRegistration), HttpStatus.CREATED);

  }
  @GetMapping("/getAllUsers")
  public ResponseEntity<List<UserRegistration>> allUsers(){
		List<UserRegistration> allUsers= userService.findAll();
		return new ResponseEntity<List<UserRegistration>>(allUsers, HttpStatus.ACCEPTED);
	
  }

  @PostMapping("/login")
// @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "Resource was not found on the server")
	public JwtTokenString generateToken(@RequestBody LoginRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new UserNotValidException("invalid username/password");
		}
		JwtTokenString jwtTokenString = new JwtTokenString();
		jwtTokenString.setToken(jwtUtil.generateToken(authRequest.getUsername()));
		return jwtTokenString;
	}
  @DeleteMapping("/deleteByUserId/{id}")
  public ResponseEntity<Map<String, Boolean>> deleteuser(@PathVariable Long id) {
      userService.deleteByUSerId(id);
      Map<String, Boolean> map = new HashMap<>();
      map.put("success", true);
      return new ResponseEntity<>(map, HttpStatus.OK);



 }
  /*
   * @PutMapping("/updateUser/{id}") public ResponseEntity<UserRegistration>
   * updateUser(@PathVariable long id,@RequestBody UserRegistration user) { return
   * userService.saveUser(user); UserRegistration updateuser =
   * userService.updateUser(user); .orElseThrow(() -> new
   * ResourceNotFoundException("Employee not exist with id: " + id));
   *
   * updateEmployee.setFirstName(employeeDetails.getFirstName());
   * updateEmployee.setLastName(employeeDetails.getLastName());
   * updateEmployee.setEmailId(employeeDetails.getEmailId());
   *
   * employeeRepository.save(updateEmployee);
   *
   * return ResponseEntity.ok(updateEmployee); }*/
	/*
	 * @GetMapping("/hello") public String hello() { return "Hello"; }
	 */
	
}
