package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.DiaNoHabil;

public class DiaNoHabilDAO {

	public static DiaNoHabil find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		DiaNoHabil diaNoHabil = null;
		
		try{
			diaNoHabil = entityManager.find(DiaNoHabil.class, id);
		}
		catch(Exception ex){
			System.err.println("Error en DiaNOHabilDAO.find" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
		return diaNoHabil;
		
	}

	public static void create(DiaNoHabil diaNoHabil){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		tr.begin();
		
		try{
			entityManager.persist(diaNoHabil);
			tr.commit();
			System.out.println("Creación de diaNoHabil:" + diaNoHabil.getFecha() + ", de motivo: " + diaNoHabil.getMotivo() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en DiaNoHabilDAO.create" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(DiaNoHabil diaNoHabil){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		tr.begin();
		
		try{
			entityManager.merge(diaNoHabil);
			tr.commit();
			System.out.println("Actualización de diaNoHabil exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en DiaNoHabil.update" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
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
			entityManager.remove(DiaNoHabilDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de diaNoHabil exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en DiaNoHabil.delete" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
