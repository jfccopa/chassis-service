package com.threetrack.repository.postgress;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnection {
	// Connection to database
	private static EntityManagerFactory connection = buildEntityManagerFactory();

	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("threeTrackConnection");
			System.out.println("Connection done");
			return managerFactory;
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
