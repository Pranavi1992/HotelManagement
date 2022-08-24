package com.project.hm.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotValidException extends RuntimeException{
	
	
	public UserNotValidException(String message)
	{
		super(message);
	}

//	public UserNotValidException(HttpStatus notFound, String message) {
//	super(404,message);
//	}
//
}
