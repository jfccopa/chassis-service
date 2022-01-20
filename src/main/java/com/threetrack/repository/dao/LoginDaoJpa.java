package com.threetrack.repository.dao;

import com.threetrack.entity.Login;
import com.threetrack.repository.config.JPAConnection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class LoginDaoJpa implements LoginDao {

	@Override
	public boolean registerLogin(Login login) {
		EntityManagerFactory factorySession = JPAConnection.getJPAFactory();
		EntityManager manager = factorySession.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {

			tx.begin();
			manager.persist(login);
			tx.commit();
			return true;

		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
			return false;
		} finally {
			manager.close();
		}
	}

	@Override
	public boolean registerLogout(Login logout) {

		EntityManagerFactory factoriaSession = JPAConnection.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		try {

			tx.begin();

			String sql = "update Login l set l.logoutDate=?2 where l.loginId=?1 and l.logoutDate is null";
			Query query = manager.createQuery(sql);

			query.setParameter(1, logout.getLoginId());
			query.setParameter(2, logout.getLogoutDate());

			Boolean result = query.executeUpdate() == 1;
			tx.commit();

			return result;

		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
			return false;
		} finally {
			manager.close();
		}
	}

	@Override
	public List<Login> list() {
		EntityManagerFactory factorySession = JPAConnection.getJPAFactory();
		EntityManager manager = factorySession.createEntityManager();

		try {
			String sql = "select l from Login l";
			TypedQuery<Login> query = manager.createQuery(sql, Login.class);
			return query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			manager.close();
		}
	}
}
