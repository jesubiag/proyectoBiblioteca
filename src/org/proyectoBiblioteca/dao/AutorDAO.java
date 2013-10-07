package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Autor;

public class AutorDAO {

	public static Autor find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Autor autor = null;
		
		try{
			autor = em.find(Autor.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de AutorDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return autor;
		
	}

	public static void create(Autor autor){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(autor);
			tr.commit();
			System.out.println("Creación de autor nro:" + autor.getId() + ", de nombre: " + autor.getNombre() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(autor) de AutorDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Autor autor){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(autor);
			tr.commit();
			System.out.println("Actualización de autor exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(autor) de AutorDAO");
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
			em.remove(AutorDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de autor exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de AutorDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
