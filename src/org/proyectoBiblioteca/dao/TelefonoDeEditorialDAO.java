package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.TelefonoDeEditorial;

public class TelefonoDeEditorialDAO {

	public static TelefonoDeEditorial find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		TelefonoDeEditorial telefonoDeEditorial = null;
		
		try{
			telefonoDeEditorial = entityManager.find(TelefonoDeEditorial.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de TelefonoDeEditorialDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
		return telefonoDeEditorial;
		
	}

	public static void create(TelefonoDeEditorial telefonoDeEditorial){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(telefonoDeEditorial);
			tr.commit();
			System.out.println("Creación de telefonoDeEditorial:" + telefonoDeEditorial.getTelefono() + ", para la editorial: " + telefonoDeEditorial.getEditorial().getNombre() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(telefonoDeEditorial) de TelefonoDeEditorialDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(TelefonoDeEditorial telefonoDeEditorial){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(telefonoDeEditorial);
			tr.commit();
			System.out.println("Actualización de telefonoDeEditorial exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(telefonoDeEditorial) de TelefonoDeEditorialDAO");
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
			entityManager.remove(TelefonoDeEditorialDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de telefonoDeEditorial exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de TelefonoDeEditorialDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
