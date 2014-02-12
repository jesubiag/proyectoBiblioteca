package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Editorial;

public class EditorialDAO {

	public static Editorial find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Editorial editorial = null;
		
		try{
			editorial = entityManager.find(Editorial.class, id);
		}
		catch(Exception ex){
			System.err.println("Error en EditorialDAO.find" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
		return editorial;
		
	}

	public static void create(Editorial editorial){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(editorial);
			tr.commit();
			System.out.println("Creación de editorial nro:" + editorial.getId() + ", de nombre: " + editorial.getNombre() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en EditorialDAO.create" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Editorial editorial){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(editorial);
			tr.commit();
			System.out.println("Actualización de editorial exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en EditorialDAO.update" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void delete(long id){

		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.remove(entityManager.merge(EditorialDAO.find(id)));
			tr.commit();
			System.out.println("Eliminación de editorial exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en EditorialDAO.delete" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
