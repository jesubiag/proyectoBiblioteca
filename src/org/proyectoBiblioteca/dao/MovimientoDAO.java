package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Movimiento;

public class MovimientoDAO {

	public static Movimiento find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Movimiento movimiento = null;
		
		try{
			movimiento = entityManager.find(Movimiento.class, id);
		}
		catch(Exception ex){
			System.err.println("Error en MovimientoDAO.find" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
		return movimiento;
		
	}

	public static void create(Movimiento movimiento){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(movimiento);
			tr.commit();
			System.out.println("Creación de movimiento nro:" + movimiento.getId() + " exitosa");
		}
		catch(Exception ex){
			System.err.println("Error en MovimientoDAO.create" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Movimiento movimiento){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(movimiento);
			tr.commit();
			System.out.println("Actualización de movimiento exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en MovimientoDAO.update" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
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
			entityManager.remove(entityManager.merge(MovimientoDAO.find(id)));
			tr.commit();
			System.out.println("Eliminación de movimiento exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en MovimientoDAO.delete" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
