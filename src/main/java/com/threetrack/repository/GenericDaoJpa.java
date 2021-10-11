package com.threetrack.repository;

import com.threetrack.entity.GenericEntity;
import com.threetrack.repository.config.JPAConnection;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.EntityType;

public abstract class GenericDaoJpa<T extends GenericEntity, I extends Serializable> implements GenericDao<T, I> {

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	protected GenericDaoJpa() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T findById(I id) {
		EntityManagerFactory factorySession = JPAConnection.getJPAFactory();
		EntityManager manager = factorySession.createEntityManager();

		try {
			return manager.find(entityClass, id);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public List<T> list() {
		EntityManagerFactory factorySession = JPAConnection.getJPAFactory();
		EntityManager manager = factorySession.createEntityManager();

		try {
			String sql = String.format("select o from %s o where o.deleted=false", entityClass.getName());
			TypedQuery<T> query = manager.createQuery(sql, entityClass);
			return query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public void add(T entity, Integer userId) {
		EntityManagerFactory factorySession = JPAConnection.getJPAFactory();
		EntityManager manager = factorySession.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {

			tx.begin();
			entity.prepareCreate(userId);
			manager.persist(entity);
			tx.commit();

		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public void update(T entity, Integer userId) {
		EntityManagerFactory factorySession = JPAConnection.getJPAFactory();
		EntityManager manager = factorySession.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {

			tx.begin();
			entity.prepareUpdate(userId);
			manager.merge(entity);
			tx.commit();

		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public void delete(I id, Integer userId) {
		EntityManagerFactory factorySession = JPAConnection.getJPAFactory();
		EntityManager manager = factorySession.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {

			final EntityType<?> entityType = manager.getMetamodel().entity(entityClass);
			String nameId = entityType.getId(entityType.getIdType().getJavaType()).getName();

			String sql =
			    String.format("update %s set deleted=?2, updateId=?3, updateDate=?4 where %s=?1", entityClass.getName(), nameId);

			Query query = manager.createQuery(sql);
			query.setParameter(1, id);
			query.setParameter(2, true);
			query.setParameter(3, userId);
			query.setParameter(4, new Date());

			tx.begin();
			query.executeUpdate();
			tx.commit();

		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public void deleteAbsolute(T entity) {
		EntityManagerFactory factorySession = JPAConnection.getJPAFactory();
		EntityManager manager = factorySession.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.remove(manager.merge(entity));
			tx.commit();

		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}
}
