package com.dulich.dto;

public class UsernameExistsException extends RuntimeException {
	public UsernameExistsException(String message) {
        super(message);
    }
}
