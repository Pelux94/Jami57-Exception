package com.azienda.catalogoProdotti.exception;

public class ItemSupplyQuantityBelowOneException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemSupplyQuantityBelowOneException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemSupplyQuantityBelowOneException(String message) {
		super(message);
	}
	
}
