package com.azienda.catalogoProdotti.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.catalogoProdotti.model.ShoppingCart;

public class ShoppingCartDao implements DaoInterface<ShoppingCart>{
	
	private EntityManager mng;
	
	public ShoppingCartDao(EntityManager mng) {
		this.mng = mng;
	}

	@Override
	public ShoppingCart create(ShoppingCart obj) {
		mng.persist(obj);
		return obj;
	}

	@Override
	public List<ShoppingCart> retrive() {
		return mng.createQuery("select sc from ShoppingCart sc",ShoppingCart.class).getResultList();
	}

	@Override
	public ShoppingCart update(ShoppingCart obj) {
		mng.persist(obj);
		return obj;
	}

	@Override
	public void delete(ShoppingCart obj) {
		mng.remove(obj);
	}
	
	public ShoppingCart getShoppingCartByID(Integer id) {
		return mng.createQuery("select sc from ShoppingCart sc where sc.id = :scid", ShoppingCart.class).setParameter("scid", id).getSingleResult();
	}

}
