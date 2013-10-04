package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

	private static final PersistenceManager singleton = new PersistenceManager();
  
	protected EntityManagerFactory entityManagerFactory;
  
	public static PersistenceManager getInstance() {
		return singleton;
	}
  
	private PersistenceManager() {
	}
 
	public EntityManagerFactory getEntityManagerFactory() {
    
		if (entityManagerFactory == null){
			createEntityManagerFactory();
		}
		return entityManagerFactory;
	}
  
	public void closeEntityManagerFactory() {
    
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
			entityManagerFactory = null;
		}
	}
  
	protected void createEntityManagerFactory() {
    
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
	}
}

