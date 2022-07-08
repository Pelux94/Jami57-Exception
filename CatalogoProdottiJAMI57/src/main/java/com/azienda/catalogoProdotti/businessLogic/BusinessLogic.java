package com.azienda.catalogoProdotti.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.catalogoProdotti.dao.ItemDao;
import com.azienda.catalogoProdotti.dao.ProfileDao;
import com.azienda.catalogoProdotti.dao.ShoppingCartDao;
import com.azienda.catalogoProdotti.dao.UserDao;
import com.azienda.catalogoProdotti.exception.ItemAlreadyExistsException;
import com.azienda.catalogoProdotti.exception.ItemIDNotFoundException;
import com.azienda.catalogoProdotti.exception.UserDoesNotExistException;
import com.azienda.catalogoProdotti.model.Item;
import com.azienda.catalogoProdotti.model.Profile;
import com.azienda.catalogoProdotti.model.ShoppingCart;
import com.azienda.catalogoProdotti.model.User;
import com.azienda.catalogoProdotti.utility.ItemValidator;
import com.azienda.catalogoProdotti.utility.ShoppingCartValidator;
import com.azienda.catalogoProdotti.utility.UserValidator;

import net.bytebuddy.asm.Advice.Return;

public class BusinessLogic {
	
	private EntityManager mng;
	private ProfileDao pDao;
	private UserDao uDao;
	private ItemDao iDao;
	private ShoppingCartDao scDao;
	
	public BusinessLogic(EntityManager mng, ProfileDao pDao, UserDao uDao, ItemDao iDao, ShoppingCartDao scDao) {
		this.mng = mng;
		this.pDao = pDao;
		this.uDao = uDao;
		this.iDao = iDao;
		this.scDao = scDao;
	}
	
	public User Login(User user) throws Exception {
		mng.getTransaction().begin();
		try {
			UserValidator.validateLoginFields(user);
			User u = UserValidator.checkIfUserExists(user.getUsername(), user.getPassword(), uDao);
			mng.getTransaction().commit();
			return u;
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public User isAdmin(User user) throws Exception {
		mng.getTransaction().begin();
		try {
			UserValidator.validateLoginFields(user);
			UserValidator.checkIfIsAdmin(user.getUsername(), user.getPassword(), uDao);
			User u = uDao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
			mng.getTransaction().commit();
			return u;
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public void registerUser(User user) throws Exception {
		mng.getTransaction().begin();
		try {
				UserValidator.validateLoginFields(user);
				UserValidator.checkUserDuplicates(user, uDao);
				Profile p = new Profile(2,"user");
				ShoppingCart sc = new ShoppingCart();
				scDao.create(sc);
				user.setProfile(p);
				user.setShoppingCart(sc);
				uDao.create(user);
			mng.getTransaction().commit();
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<User> getUserList(){
		mng.getTransaction().begin();
		try {
			List<User> user = uDao.retrive();
			for(User u : user) {
				u.getProfile().getName();
			}
			mng.getTransaction().commit();
			return user;
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}

	}
	
	public void addItem(Item item) throws Exception {
		mng.getTransaction().begin();
		try {
				ItemValidator.validateItemEntryFields(item);
				ItemValidator.checkItemDuplicates(item.getItemName(), iDao);
				iDao.create(item);
			mng.getTransaction().commit();
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<Item> getItems(String name, Float price) throws Exception {
		mng.getTransaction().begin();
		try {
			List<Item> items;
					if(ItemValidator.validateItemNameField(name, price)) {
						items = iDao.getItemByLikeName(name);
						for(Item i : items) {
							System.out.println(i);
						}
					} else if(ItemValidator.validateItemPriceField(name, price)) {
						items = iDao.getItemByLowerPrice(price);
						for(Item i : items) {
							System.out.println(i);
						}
					} else if(ItemValidator.validateItemNameAndPriceFields(name, price)) {
						items = iDao.getItemByLikeNameAndLowerPrice(name, price);
						for(Item i : items) {
							System.out.println(i);
						}
					} else {
						items = iDao.retrive();
						for(Item i : items) {
							System.out.println(i);
						}
					}
					ItemValidator.itemListIsNull(items);
			mng.getTransaction().commit();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public void updateItem(String name, Float price, Integer supply, Integer id) throws Exception {
		mng.getTransaction().begin();
		try {
			ItemValidator.validateIDField(id);
			ItemValidator.validateItemEntryFieldsUpdate(name, price, supply);
			Item i = ItemValidator.checkItemIfExistsByID(id, iDao);
			i.setItemName(name);
			i.setPrice(price);
			i.setSupply(supply);
			mng.getTransaction().commit();
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public void deleteItem(Integer id) throws Exception {
		mng.getTransaction().begin();
		try {
			if( id == null) {
				throw new ItemIDNotFoundException("ID not found");
			}
			Item i = ItemValidator.checkItemIfExistsByID(id, iDao);
			List<ShoppingCart> sc = i.getShoppingCarts();
			if(sc != null) {
				iDao.delete(i);
			}
			mng.getTransaction().commit();
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public void addToShoppingCart(Integer id, User user) throws Exception {
		mng.getTransaction().begin();
		try {
			if(id == null) {
				throw new ItemIDNotFoundException("ID not found");
			}
			Item i = ItemValidator.checkItemIfExistsByID(id, iDao);
			if(user.getShoppingCart() == null) {
				ShoppingCart sc = new ShoppingCart();
				scDao.create(sc);
				user.setShoppingCart(sc);
			} 
			ShoppingCart sDB = user.getShoppingCart();
			List<Item> items = sDB.getItems();
			if(!items.contains(i)) {
				items.add(i);
			} else {
				throw new ItemAlreadyExistsException(i.getItemName() + " is already in your cart");
			}
			mng.merge(sDB);
			mng.getTransaction().commit();
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public void removeFromShoppingCart(Integer id, User user) throws Exception {
		mng.getTransaction().begin();
		try {
			if(id == null) {
				throw new ItemIDNotFoundException("ID not found");
			}
			Item i = ItemValidator.checkItemIfExistsByID(id, iDao);
			ShoppingCart sc = user.getShoppingCart();
			List<Item> items = sc.getItems();
			if(items.contains(i)) {
				items.remove(i);
				mng.merge(sc);
			} else {
				throw new ItemIDNotFoundException("Product can not be removed");
			}
			
			mng.getTransaction().commit();
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<Item> getItemsFromCart(Integer id) throws Exception{
		mng.getTransaction().begin();
		try {
			if(id == null) {
				throw new ItemIDNotFoundException("ID not found");
			}	
			ShoppingCart sc = ShoppingCartValidator.checkIfShoppingCartExists(id, scDao);
			List<Item> items = sc.getItems();
			for(Item i : items) {
				System.out.println(i);
			}
			mng.getTransaction().commit();
			return items;
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}
	
	public void buyItem(Integer id, User user) throws Exception {
		mng.getTransaction().begin();
		try {
			if(id == null) {
				throw new ItemIDNotFoundException("ID not found");
			}
			UserValidator.checkIfUserHasShoppingCart(user);
			Item i = ItemValidator.checkItemIfExistsByID(id, iDao);
			ItemValidator.checkSupplyQuantity(i);
			
			Integer x = i.getSupply();
			--x;
			i.setSupply(x);
			ShoppingCart sc = user.getShoppingCart();
			List<Item> items = sc.getItems();
			if(items.contains(i)) {
				items.remove(i);
				mng.merge(sc);
			} else {
				throw new ItemIDNotFoundException("Product is currently out of stock");
			}
			mng.getTransaction().commit();
		} catch (Exception e) {
			mng.getTransaction().rollback();
			throw e;
		}
	}

	
}
