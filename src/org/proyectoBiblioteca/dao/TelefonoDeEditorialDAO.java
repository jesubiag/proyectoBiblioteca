package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.TelefonoDeEditorial;

public class TelefonoDeEditorialDAO {

	public static TelefonoDeEditorial find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		TelefonoDeEditorial telefonoDeEditorial = null;
		
		try{
			telefonoDeEditorial = em.find(TelefonoDeEditorial.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de TelefonoDeEditorialDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return telefonoDeEditorial;
		
	}

	public static void create(TelefonoDeEditorial telefonoDeEditorial){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(telefonoDeEditorial);
			tr.commit();
			System.out.println("Creación de telefonoDeEditorial:" + telefonoDeEditorial.getTelefono() + ", para la editorial: " + telefonoDeEditorial.getEditorial().getNombre() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(telefonoDeEditorial) de TelefonoDeEditorialDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(TelefonoDeEditorial telefonoDeEditorial){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(telefonoDeEditorial);
			tr.commit();
			System.out.println("Actualización de telefonoDeEditorial exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(telefonoDeEditorial) de TelefonoDeEditorialDAO");
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
			em.remove(TelefonoDeEditorialDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de telefonoDeEditorial exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de TelefonoDeEditorialDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
