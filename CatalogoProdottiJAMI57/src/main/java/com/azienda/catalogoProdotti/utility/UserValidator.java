package com.azienda.catalogoProdotti.utility;

import java.util.List;

import com.azienda.catalogoProdotti.dao.UserDao;
import com.azienda.catalogoProdotti.exception.UserAlreadyExistsException;
import com.azienda.catalogoProdotti.exception.UserDoesNotExistException;
import com.azienda.catalogoProdotti.exception.UserDoesNotHaveShoppingCartException;
import com.azienda.catalogoProdotti.exception.UserUsernameAndPasswordAreEmptyException;
import com.azienda.catalogoProdotti.model.ShoppingCart;
import com.azienda.catalogoProdotti.model.User;

public class UserValidator {
	
	public static void validateLoginFields(User user) throws UserUsernameAndPasswordAreEmptyException {
		if(user.getUsername() == null || user.getPassword() == null 
				|| user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			throw new UserUsernameAndPasswordAreEmptyException("One or more fields are empty");
		}
	}
	
	public static User checkIfUserExists(String name, String password, UserDao uDao) throws UserDoesNotExistException {
		User uDB = uDao.getUserByUsernameAndPassword(name, password);
		if(uDB.getUsername() == null || uDB.getPassword() == null) {
			throw new UserDoesNotExistException("User does not exist");
		}
		return uDB;
	}
	
	public static void checkUserDuplicates(User user, UserDao uDao) throws UserAlreadyExistsException {
		List<User> userList = uDao.getUserUsernameList(user.getUsername());
		if(userList.size() > 0) {
			throw new UserAlreadyExistsException("Username already taken");
		}
	}
	
	public static boolean checkIfIsAdmin(String name, String password, UserDao uDao) {
		User uDB = uDao.getUserByUsernameAndPassword(name, password);
		if(uDB != null && uDB.getProfile().getName().equals("admin")) {
			return true;
		}
		return false;
	}
	
	public static ShoppingCart checkIfUserHasShoppingCart(User user) throws UserDoesNotHaveShoppingCartException {
		ShoppingCart sc = user.getShoppingCart();
		if(sc == null) {
			throw new UserDoesNotHaveShoppingCartException("This user does not have an associated Shopping Cart");
		}
		return sc;
	}

}
