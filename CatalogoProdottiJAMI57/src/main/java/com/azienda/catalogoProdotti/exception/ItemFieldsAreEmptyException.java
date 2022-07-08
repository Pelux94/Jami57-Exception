package com.azienda.catalogoProdotti.exception;

public class ItemFieldsAreEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemFieldsAreEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemFieldsAreEmptyException(String message) {
		super(message);
	}
	
}
