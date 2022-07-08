package com.azienda.catalogoProdotti.utility;

import com.azienda.catalogoProdotti.dao.ShoppingCartDao;
import com.azienda.catalogoProdotti.exception.ShoppingCartDoesNotExistException;
import com.azienda.catalogoProdotti.exception.ShoppingCartIDFieldNotValidException;
import com.azienda.catalogoProdotti.model.ShoppingCart;

public class ShoppingCartValidator {
	
	public static void validateShoppingCartIDField(ShoppingCart sc) throws ShoppingCartIDFieldNotValidException {
		if(sc.getId() == null || sc.getId() < 0) {
			throw new ShoppingCartIDFieldNotValidException("The entered ID does not exist/is not valid");
		}
	}
	
	public static ShoppingCart checkIfShoppingCartExists(Integer id, ShoppingCartDao scDao) throws ShoppingCartDoesNotExistException {
		ShoppingCart sc = scDao.getShoppingCartByID(id);
		if(sc.getId() == null || sc.getId() < 0) {
			throw new ShoppingCartDoesNotExistException("The shopping cart does not exist yet");
		}
		return sc;
	}

}
