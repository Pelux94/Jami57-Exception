package com.azienda.catalogoProdotti.utility;

import java.util.List;

import com.azienda.catalogoProdotti.dao.ItemDao;
import com.azienda.catalogoProdotti.exception.ItemAlreadyExistsException;
import com.azienda.catalogoProdotti.exception.ItemFieldsAreEmptyException;
import com.azienda.catalogoProdotti.exception.ItemFieldsToUpdateAreEmptyException;
import com.azienda.catalogoProdotti.exception.ItemIDNotFoundException;
import com.azienda.catalogoProdotti.exception.ItemSearchMatchException;
import com.azienda.catalogoProdotti.exception.ItemSupplyQuantityBelowOneException;
import com.azienda.catalogoProdotti.model.Item;

public class ItemValidator {
	
	public static void validateItemEntryFields(Item item) throws ItemFieldsAreEmptyException {
		if(item == null || item.getItemName() == null || item.getPrice() == null || item.getSupply() == null
				|| item.getItemName().isEmpty() || item.getPrice().isNaN() || item.getPrice() < 0 || item.getSupply() <= 0) {
			throw new ItemFieldsAreEmptyException("One or more fields are empty or not valid");
		}
	}
	
	public static void validateItemEntryFieldsUpdate(String name, Float price, Integer supply) throws ItemFieldsAreEmptyException {
		if(name == null || price == null || supply == null
				|| name.isEmpty() || price.isNaN() || price <= 0 || supply <= 0) {
			throw new ItemFieldsAreEmptyException("One or more fields are empty or not valid");
		}
	}
	
	public static void validateItemFields(Item item) throws ItemFieldsAreEmptyException {
		if(item.getItemName() == null || item.getPrice() == null 
				|| item.getItemName().isEmpty() || item.getPrice().isNaN() || item.getPrice() < 0) {
			throw new ItemFieldsAreEmptyException("One or more fields are empty or not valid");
		}
	}
	
	public static void validateIDField(Integer id) throws ItemIDNotFoundException {
		if(id == null && id <= 0) {
			throw new ItemIDNotFoundException("ID not found");
		}
	}
	
	public static void checkItemDuplicates(String name, ItemDao iDao) throws ItemAlreadyExistsException {
		List<Item> items = iDao.getItemByName(name);
		if(items.size() > 0) {
			throw new ItemAlreadyExistsException("Product already exists");
		}
	}
	
	public static boolean validateItemNameField(String name, Float price) {
		if(name != null && !name.isEmpty() && price == null) {
			return true;
		}
		return false;
	}
	
	public static boolean validateItemPriceField(String name, Float price) {
		if((name == null || name.isEmpty() || name.equals("%%")) && (price != null && price >= 0)) {
			return true;
		}
		return false;
	}
	
	public static boolean validateItemNameAndPriceFields(String name, Float price) {
		if(name != null && !name.isEmpty() && price != null && price >= 0){
			return true;
		}
		return false;
		
	}
	
	public static void validateItemFieldsToUpdate(Item item) throws ItemFieldsToUpdateAreEmptyException {
		if(item == null || item.getItemName() == null || item.getId() == null || item.getPrice() == null || item.getSupply() == null
				|| item.getItemName().isEmpty() || item.getPrice() <= 0 || item.getSupply() <= 0);
		throw new ItemFieldsToUpdateAreEmptyException("One or more fields are empty or not valid");
	}
	
	public static Item checkItemIfExistsByID(Integer id, ItemDao iDao) throws ItemIDNotFoundException {
		Item item = iDao.getItemByID(id);
		if(item.getId() == null) {
			throw new ItemIDNotFoundException("Product ID does not exist");
		}
		return item;
	}
	
	public static void checkSupplyQuantity(Item item) throws ItemSupplyQuantityBelowOneException {
		if(item.getSupply() <= 1 || item.getSupply() == null) {
			throw new ItemSupplyQuantityBelowOneException("Product is currently out of stock");
		}
	}
	
	public static Item findItem(Item item, ItemDao iDao) {
		Item itemDb = iDao.getItemByName2(item.getItemName());
		return itemDb;
	}
	
	public static void itemListIsNull(List<Item> item) throws ItemSearchMatchException{
		if(item.isEmpty() || item.size()<=0) {
			throw new ItemSearchMatchException("Product name or price not found");
		}
	}

}
