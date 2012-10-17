package com.application.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface BasisDao<T, ID extends Serializable> {
	
	public void save(T entity);

	public void update(T entity);

	public List<T> findAll();

	public void delete(T entity);
	
	public void deleteAll(Collection<T> entities) throws DataAccessException;

	public List<T> findByExample(T exampleInstance, String... excludeProperty);

	public int count();
	
	T findById(ID id, boolean lock);


}
