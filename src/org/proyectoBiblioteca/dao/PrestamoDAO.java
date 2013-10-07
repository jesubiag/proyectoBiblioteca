package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Prestamo;

public class PrestamoDAO {
	
	public static Prestamo find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Prestamo prestamo = null;
		
		try{
			prestamo = em.find(Prestamo.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(nombre) de PrestamoDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return prestamo;
		
	}

	public static void create(Prestamo prestamo){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(prestamo);
			tr.commit();
			System.out.println("Creación de prestamo " + prestamo.getId() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(prestamo) de PrestamoDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Prestamo prestamo){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(prestamo);
			tr.commit();
			System.out.println("Actualización de prestamo exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(prestamo) de PrestamoDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void delete(long id){

		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.remove(PrestamoDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de prestamo exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de PrestamoDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
