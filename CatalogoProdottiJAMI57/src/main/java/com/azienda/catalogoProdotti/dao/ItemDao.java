package com.azienda.catalogoProdotti.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.catalogoProdotti.model.Item;

public class ItemDao implements DaoInterface<Item> {
	
	private EntityManager mng;
	
	public ItemDao(EntityManager mng) {
		this.mng = mng;
	}

	@Override
	public Item create(Item obj) {
		mng.persist(obj);
		return obj;
	}

	@Override
	public List<Item> retrive() {
		return mng.createQuery("select i from Item i", Item.class).getResultList();
	}

	@Override
	public Item update(Item obj) {
		mng.persist(obj);
		return obj;
	}

	@Override
	public void delete(Item obj) {
		mng.remove(obj);
	}
	
	public Item getItemByID(Integer id) {
		return mng.createQuery("select i from Item i where i.id = :nid", Item.class).setParameter("nid", id).getSingleResult();
	}
	
	public List<Item> getItemByLikeName(String name) { //si fa così? o con %:n%
		return mng.createQuery("select i from Item i where i.itemName like :n", Item.class).setParameter("n", name).getResultList();
	}
	
	public List<Item> getItemByName(String name) {
		return mng.createQuery("select i from Item i where i.itemName = :n", Item.class).setParameter("n", name).getResultList();
	}
	
	public List<Item> getItemByLowerPrice(Float price){
		return mng.createQuery("select i from Item i where i.price <= :p",Item.class).setParameter("p", price).getResultList();
	}
	
	public List<Item> getItemByLikeNameAndLowerPrice(String name, Float price){
		return mng.createQuery("select i from Item i where i.itemName like :n and i.price <= :p", Item.class)
				.setParameter("n", name).setParameter("p", price).getResultList();
	}
	
	public Item getItemByName2(String itemName) {
		return mng.createQuery("select i from Item i where i.itemName = :n",Item.class)
				.setParameter("n", itemName).getSingleResult();
	}
	
	

}
