package com.threetrack.repository.dao;

import com.threetrack.entity.User;
import com.threetrack.repository.GenericDaoJpa;
import com.threetrack.repository.config.JPAConnection;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Component
public class UserDaoJpa extends GenericDaoJpa<User, Integer> implements UserDao {

	@Override
	public User findUser(String userName, String password) {
		EntityManagerFactory factorySession = JPAConnection.getJPAFactory();
		EntityManager manager = factorySession.createEntityManager();

		try {
			String sql = "select u from User u where u.name=?1 and u.password=?2 and u.deleted=false";
			TypedQuery<User> query = manager.createQuery(sql, User.class);
			query.setParameter(1, userName);
			query.setParameter(2, password);

			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			manager.close();
		}
	}
}
