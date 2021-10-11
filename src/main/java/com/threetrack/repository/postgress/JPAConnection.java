package com.threetrack.repository.postgress;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnection {
	// Connection to database
	private static EntityManagerFactory connection;

	private JPAConnection() {
		throw new IllegalStateException();
	}

	public static void buildEntityManagerFactory(Properties properties) {
		try {
			connection = Persistence.createEntityManagerFactory("threeTrackConnection", properties);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error establishing a database connection");
		}
	}

	// Method get to connection
	public static EntityManagerFactory getJPAFactory() {
		return connection;
	}
}
