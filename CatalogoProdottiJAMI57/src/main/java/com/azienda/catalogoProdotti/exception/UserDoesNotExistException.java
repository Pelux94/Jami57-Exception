package com.azienda.catalogoProdotti.exception;

public class UserDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDoesNotExistException(String message) {
		super(message);
	}
	
}
