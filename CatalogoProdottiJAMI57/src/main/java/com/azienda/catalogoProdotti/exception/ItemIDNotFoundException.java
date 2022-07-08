package com.azienda.catalogoProdotti.exception;

public class ItemIDNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemIDNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemIDNotFoundException(String message) {
		super(message);
	}
	
}
