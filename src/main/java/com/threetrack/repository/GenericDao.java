package com.threetrack.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, Id extends Serializable> {
	T findById(Id id);
	List<T> list();
	void add(T entity);
	void update(T entity);
	void delete(Id id);
	void delete(T entity);
}
