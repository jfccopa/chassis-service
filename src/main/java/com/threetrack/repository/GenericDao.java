package com.threetrack.repository;

import com.threetrack.entity.GenericEntity;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends GenericEntity, I extends Serializable> {
	T findById(I id);
	List<T> list();
	void add(T entity, Integer userId);
	void update(T entity, Integer userId);
	void delete(I id, Integer userId);
	void deleteAbsolute(T entity);
}
