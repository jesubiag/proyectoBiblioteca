package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Provincia;

public class ProvinciaDAO {

	public static Provincia find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Provincia provincia = null;
		
		try{
			provincia = em.find(Provincia.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de ProvinciaDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return provincia;
		
	}

	public static void create(Provincia provincia){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(provincia);
			tr.commit();
			System.out.println("Creación de provincia:" + provincia.getNombre() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(provincia) de ProvinciaDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Provincia provincia){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(provincia);
			tr.commit();
			System.out.println("Actualización de provincia exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(provincia) de ProvinciaDAO");
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
			em.remove(ProvinciaDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de provincia exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de ProvinciaDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
