package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Ejemplar;

public class EjemplarDAO {

	public static Ejemplar find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Ejemplar ejemplar = null;
		
		try{
			ejemplar = em.find(Ejemplar.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de EjemplarDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return ejemplar;
		
	}

	public static void create(Ejemplar ejemplar){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(ejemplar);
			tr.commit();
			System.out.println("Creación de ejemplar nro:" + ejemplar.getNumeroEjemplar() + ", del libro: " + ejemplar.getLibro().getTitulo() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(ejemplar) de EjemplarDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Ejemplar ejemplar){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(ejemplar);
			tr.commit();
			System.out.println("Actualización de ejemplar exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(ejemplar) de EjemplarDAO");
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
			em.remove(EjemplarDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de ejemplar exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de EjemplarDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
