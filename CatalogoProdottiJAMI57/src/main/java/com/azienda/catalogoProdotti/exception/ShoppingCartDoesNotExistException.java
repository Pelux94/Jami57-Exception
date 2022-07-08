package com.azienda.catalogoProdotti.exception;

public class ShoppingCartDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public ShoppingCartDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShoppingCartDoesNotExistException(String message) {
		super(message);
	}
	
}
