package com.project.hm.controllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.hm.customexceptions.UserNotValidException;

@RestControllerAdvice
public class ExceptionHandlerr {
	
	@ExceptionHandler(UserNotValidException.class)
	public Map<String, String>handleException(UserNotValidException exception){
		HashMap<String, String> map=new HashMap<>();
		map.put("ErrorMessage", exception.getMessage());
		
		return map;
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String>handleException(MethodArgumentNotValidException exception){
		HashMap<String, String> map=new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error->{
		map.put(error.getField(), error.getDefaultMessage());
		});
		
		return map;
	}

}
