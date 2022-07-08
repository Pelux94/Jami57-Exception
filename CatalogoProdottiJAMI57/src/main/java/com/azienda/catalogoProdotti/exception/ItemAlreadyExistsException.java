package com.azienda.catalogoProdotti.exception;

public class ItemAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemAlreadyExistsException(String message) {
		super(message);
	}
	
}
