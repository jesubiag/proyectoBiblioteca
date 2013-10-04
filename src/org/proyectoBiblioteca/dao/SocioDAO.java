package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Socio;

public class SocioDAO {

	public static Socio find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Socio socio = null;
		
		try{
			socio = em.find(Socio.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de SocioDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return socio;
		
	}

	public static void create(Socio socio){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(socio);
			tr.commit();
			System.out.println("Creación de socio " + socio.getNombre() + " " + socio.getApellido() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(socio) de SocioDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Socio socio){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(socio);
			tr.commit();
			System.out.println("Actualización de socio exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(socio) de SocioDAO");
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
			em.remove(SocioDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de socio exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de SocioDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
