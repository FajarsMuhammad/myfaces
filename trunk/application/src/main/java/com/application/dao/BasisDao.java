package com.application.dao;

import java.io.Serializable;
import java.util.List;

public interface BasisDao<T, ID extends Serializable> {
	
	public void save(T entity);

	public void update(T entity);

	public List<T> findAll();

	public void delete(T entity);

	public List<T> findByExample(T exampleInstance, String... excludeProperty);

	public int count();
	
	T findById(ID id, boolean lock);


}
