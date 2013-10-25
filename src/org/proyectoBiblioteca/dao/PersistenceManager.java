package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PersistenceManager {

	private static final PersistenceManager SINGLETON = new PersistenceManager();
  
	private EntityManagerFactory entityManagerFactory;
  
	public static PersistenceManager getInstance() {
		return SINGLETON;
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
	
	public static EntityManager getEntityManager(){
		return PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
	}
}

