package com.azienda.catalogoProdotti.dao;

import java.util.List;

public interface DaoInterface <T> {
	
	public T create (T obj);
	public List<T> retrive();
	public T update(T obj);
    public void delete(T obj);
}
