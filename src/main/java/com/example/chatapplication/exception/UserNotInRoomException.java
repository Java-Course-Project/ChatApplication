package com.example.chatapplication.exception;

public class UserNotInRoomException extends RuntimeException {
	public UserNotInRoomException(String message) {
		super(message);
	}
}
