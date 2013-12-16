package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Prestamo;

public class PrestamoDAO {
	
	public static Prestamo find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Prestamo prestamo = null;
		
		try{
			prestamo = entityManager.find(Prestamo.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(nombre) de PrestamoDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
		return prestamo;
		
	}

	public static Prestamo create(Prestamo prestamo){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(prestamo);
			tr.commit();
			System.out.println("Creación de prestamo " + prestamo.getId() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(prestamo) de PrestamoDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
		return prestamo;
	}
	
	public static void update(Prestamo prestamo){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(prestamo);
			tr.commit();
			System.out.println("Actualización de prestamo exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(prestamo) de PrestamoDAO");
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
			entityManager.remove(PrestamoDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de prestamo exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de PrestamoDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
