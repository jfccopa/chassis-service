package com.threetrack.repository.postgress;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.threetrack.entity.Product;

public class ProductDao {

	public static List<Product> list(){
		EntityManagerFactory factoriaSession = JPAConnection.getJPAFactory();
    EntityManager manager = factoriaSession.createEntityManager();

    try {
      TypedQuery<Product> query = manager.createQuery("select p from Product p", Product.class);
      return query.getResultList();

    } catch (Throwable ex) {
			ex.printStackTrace();
      throw new RuntimeException("fatal error");
    } finally {
      manager.close();
    }
	}
	
}
