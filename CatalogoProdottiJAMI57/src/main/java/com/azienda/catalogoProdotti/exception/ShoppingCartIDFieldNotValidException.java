package com.azienda.catalogoProdotti.exception;

public class ShoppingCartIDFieldNotValidException extends Exception {
	private static final long serialVersionUID = 1L;

	public ShoppingCartIDFieldNotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShoppingCartIDFieldNotValidException(String message) {
		super(message);
	}
	
}
