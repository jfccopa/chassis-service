package com.threetrack.repository.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import com.threetrack.utils.Constants;

public class JPAConnection {
	// Connection to database
	@PersistenceContext
	private static EntityManagerFactory connection;

	private JPAConnection() {
		throw new IllegalStateException();
	}

	public static void buildEntityManagerFactory(Properties properties, String nameConnection) {
		try {
			connection = Persistence.createEntityManagerFactory(nameConnection, properties);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw new RuntimeException(Constants.ERROR_DATABASE_CONNECTION);
		}
	}

	// Method get to connection
	public static EntityManagerFactory getJPAFactory() {
		return connection;
	}
}
