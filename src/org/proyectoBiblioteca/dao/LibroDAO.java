package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Libro;

public class LibroDAO {

	public static Libro find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Libro libro = null;
		
		try{
			libro = em.find(Libro.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de LibroDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return libro;
		
	}

	public static void create(Libro libro){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(libro);
			tr.commit();
			System.out.println("Creación de libro " + libro.getTitulo() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(libro) de LibroDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Libro libro){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(libro);
			tr.commit();
			System.out.println("Actualización de libro exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(libro) de LibroDAO");
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
			em.remove(LibroDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de libro exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de LibroDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
