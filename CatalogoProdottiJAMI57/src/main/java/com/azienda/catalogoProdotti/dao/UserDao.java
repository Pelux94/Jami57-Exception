package com.azienda.catalogoProdotti.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.catalogoProdotti.model.User;

public class UserDao implements DaoInterface<User> {
	
	private EntityManager mng;
	
	public UserDao(EntityManager mng) {
		this.mng = mng;
	}

	@Override
	public User create(User obj) {
		mng.persist(obj);
		return obj;
	}

	@Override
	public List<User> retrive() {
		return mng.createQuery("select u from User u", User.class).getResultList();
	}

	@Override
	public User update(User obj) {
		mng.persist(obj);
		return obj;
	}

	@Override
	public void delete(User obj) {
		mng.remove(obj);
	}
	
	public User getUserByUsername(String username) {
		return mng.createQuery("select u from User u where u.username = :us", User.class).setParameter("us", username).getSingleResult();
	}
	
	public List<User> getUserUsernameList(String name){
		return mng.createQuery("select u from User u where u.username = :us", User.class).setParameter("us", name).getResultList();
	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		return mng.createQuery("select u from User u where u.username = :us and u.password = :up", User.class)
				.setParameter("us", username).setParameter("up", password).getSingleResult();
	}
	
	

}
