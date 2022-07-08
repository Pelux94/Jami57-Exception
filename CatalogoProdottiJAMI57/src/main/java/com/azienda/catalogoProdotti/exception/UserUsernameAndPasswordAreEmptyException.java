package com.azienda.catalogoProdotti.exception;

public class UserUsernameAndPasswordAreEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserUsernameAndPasswordAreEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserUsernameAndPasswordAreEmptyException(String message) {
		super(message);
	}
	
}
