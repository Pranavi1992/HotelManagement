package com.project.hm.customexceptions;

public class UserNotValidException extends RuntimeException{
	
	public UserNotValidException(String message)
	{
		super(message);
	}

}
