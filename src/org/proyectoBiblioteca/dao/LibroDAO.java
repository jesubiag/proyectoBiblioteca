package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Libro;

public class LibroDAO {

	public static Libro find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Libro libro = null;
		
		try{
			libro = entityManager.find(Libro.class, id);
		}
		catch(Exception ex){
			System.err.println("Error en LibroDAO.find" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
		return libro;
		
	}

	public static void create(Libro libro){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(libro);
			tr.commit();
			System.out.println("Creaci�n de libro " + libro.getTitulo() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en LibroDAO.create" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Libro libro){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(libro);
			tr.commit();
			System.out.println("Actualizaci�n de libro exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en LibroDAO.update" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
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
			entityManager.remove(entityManager.merge(LibroDAO.find(id)));
			tr.commit();
			System.out.println("Eliminaci�n de libro exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en LibroDAO.delete" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
