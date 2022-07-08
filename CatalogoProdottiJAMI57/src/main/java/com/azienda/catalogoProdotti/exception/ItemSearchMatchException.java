package com.azienda.catalogoProdotti.exception;

public class ItemSearchMatchException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemSearchMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemSearchMatchException(String message) {
		super(message);
	}
	
}
