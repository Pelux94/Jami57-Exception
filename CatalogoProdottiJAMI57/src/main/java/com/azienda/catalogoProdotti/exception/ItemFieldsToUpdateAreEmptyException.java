package com.azienda.catalogoProdotti.exception;

public class ItemFieldsToUpdateAreEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemFieldsToUpdateAreEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemFieldsToUpdateAreEmptyException(String message) {
		super(message);
	}
	
}
