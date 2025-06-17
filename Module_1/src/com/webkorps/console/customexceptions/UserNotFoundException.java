package com.webkorps.console.customexceptions;

public class UserNotFoundException extends Exception{
	public UserNotFoundException(String message) {
        super(message);
    }
}
