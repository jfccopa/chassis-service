package com.threetrack.repository.postgress;

import com.threetrack.repository.GenericDao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public abstract class GenericDaoJpa<T, Id extends Serializable> implements GenericDao<T, Id> {

	private Class<T> entityType;

	@SuppressWarnings("unchecked")
	public GenericDaoJpa() {
		this.entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T findById(Id id) {
		EntityManagerFactory factoriaSession = JPAConnection.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();

		try {
			return manager.find(entityType, id);

		} catch (Throwable ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public List<T> list() {
		EntityManagerFactory factoriaSession = JPAConnection.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();

		try {
			String sql = "select e from " + entityType.getName() + " e where e.deleted=false";
			TypedQuery<T> query = manager.createQuery(sql, entityType);
			return query.getResultList();

		} catch (Throwable ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public void add(T entity) {
		EntityManagerFactory factoriaSession = JPAConnection.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.persist(entity);
			tx.commit();

		} catch (Throwable ex) {
			tx.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public void update(T entity) {
		EntityManagerFactory factoriaSession = JPAConnection.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.merge(entity);
			tx.commit();

		} catch (Throwable ex) {
			tx.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public void delete(Id id) {
		EntityManagerFactory factoriaSession = JPAConnection.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {

			String sql = "update  " + entityType.getName() + " p set p.deleted=true where p.id=?";
			TypedQuery<T> query = manager.createQuery(sql, entityType);
			query.setParameter(1, id);

			tx.begin();
			query.executeUpdate();
			tx.commit();

		} catch (Throwable ex) {
			tx.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}

	@Override
	public void delete(T entity) {
		EntityManagerFactory factoriaSession = JPAConnection.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.remove(manager.merge(entity));
			tx.commit();

		} catch (Throwable ex) {
			tx.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}
}
