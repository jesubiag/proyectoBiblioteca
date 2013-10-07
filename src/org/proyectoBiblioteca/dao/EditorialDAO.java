package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Editorial;

public class EditorialDAO {

	public static Editorial find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Editorial editorial = null;
		
		try{
			editorial = em.find(Editorial.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de EditorialDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return editorial;
		
	}

	public static void create(Editorial editorial){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(editorial);
			tr.commit();
			System.out.println("Creación de editorial nro:" + editorial.getId() + ", de nombre: " + editorial.getNombre() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(editorial) de EditorialDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Editorial editorial){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(editorial);
			tr.commit();
			System.out.println("Actualización de editorial exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(editorial) de EditorialDAO");
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
			em.remove(EditorialDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de editorial exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de EditorialDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
