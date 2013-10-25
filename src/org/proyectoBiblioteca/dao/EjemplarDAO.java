package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Ejemplar;

public class EjemplarDAO {

	public static Ejemplar find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Ejemplar ejemplar = null;
		
		try{
			ejemplar = entityManager.find(Ejemplar.class, id);
		}
		catch(Exception ex){
			System.err.println("Error en EjemplarDAO.find" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
		return ejemplar;
		
	}

	public static void create(Ejemplar ejemplar){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(ejemplar);
			tr.commit();
			System.out.println("Creación de ejemplar nro:" + ejemplar.getNumeroEjemplar() + ", del libro: " + ejemplar.getLibro().getTitulo() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en EjemplarDAO.create" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Ejemplar ejemplar){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(ejemplar);
			tr.commit();
			System.out.println("Actualización de ejemplar exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en EjemplarDAO.update" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
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
			entityManager.remove(EjemplarDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de ejemplar exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en EjemplarDAO.delete" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
