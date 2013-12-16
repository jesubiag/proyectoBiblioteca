package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Autor;

public class AutorDAO {

	public static Autor find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Autor autor = null;
		
		try{
			autor = entityManager.find(Autor.class, id);
		}
		catch(Exception ex){
			System.err.println("Error en AutorDAO.find" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
		return autor;
		
	}

	public static void create(Autor autor){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(autor);
			tr.commit();
			System.out.println("Creación de autor nro:" + autor.getId() + ", de nombre: " + autor.getNombre() + " exitosa");
		}
		catch(Exception ex){
			System.err.println("Error en AutorDAO.create" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Autor autor){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(autor);
			tr.commit();
			System.out.println("Actualización de autor exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en AutorDAO.update" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
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
			entityManager.remove(AutorDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de autor exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en AutorDAO.delete" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
