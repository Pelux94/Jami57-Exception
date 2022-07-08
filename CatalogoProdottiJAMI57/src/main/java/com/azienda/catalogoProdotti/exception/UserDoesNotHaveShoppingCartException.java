package com.azienda.catalogoProdotti.exception;

public class UserDoesNotHaveShoppingCartException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserDoesNotHaveShoppingCartException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDoesNotHaveShoppingCartException(String message) {
		super(message);
	}
	
}
