package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Suspension;

public class SuspensionDAO {

	public static Suspension find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Suspension suspension = null;
		
		try{
			suspension = entityManager.find(Suspension.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de SuspensionDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
		return suspension;
		
	}

	public static void create(Suspension suspension){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(suspension);
			tr.commit();
			System.out.println("Creación de suspension al socio:" + suspension.getSocio().getNombre() + " " + suspension.getSocio().getApellido() + ", de motivo: " + suspension.getMotivo() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(suspension) de SuspensionDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Suspension suspension){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(suspension);
			tr.commit();
			System.out.println("Actualización de suspension exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(suspension) de SuspensionDAO");
			ex.printStackTrace();
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
			entityManager.remove(SuspensionDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de suspension exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de SuspensionDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
