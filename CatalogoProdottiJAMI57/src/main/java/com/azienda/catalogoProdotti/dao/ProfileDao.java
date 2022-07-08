package com.azienda.catalogoProdotti.dao;

import java.rmi.Remote;
import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.catalogoProdotti.model.Profile;

public class ProfileDao implements DaoInterface<Profile> {
	
	private EntityManager mng;
	
	public ProfileDao(EntityManager mng) {
		this.mng = mng;
	}

	@Override
	public Profile create(Profile obj) {
		mng.persist(obj);
		return obj;
	}

	@Override
	public List<Profile> retrive() {
		return mng.createQuery("select p from Profile p", Profile.class).getResultList();
	}

	@Override
	public Profile update(Profile obj) {
		mng.persist(obj);
		return obj;
	}

	@Override
	public void delete(Profile obj) {
		mng.remove(obj);
		
	}
	
	public Profile getProfileByName(String name) {
		return mng.createQuery("select p from Profile p where p.name = :n",Profile.class).setParameter("n", name).getSingleResult();
	}

}
