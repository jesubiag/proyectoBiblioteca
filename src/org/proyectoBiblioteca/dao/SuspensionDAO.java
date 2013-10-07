package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Suspension;

public class SuspensionDAO {

	public static Suspension find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Suspension suspension = null;
		
		try{
			suspension = em.find(Suspension.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de SuspensionDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return suspension;
		
	}

	public static void create(Suspension suspension){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(suspension);
			tr.commit();
			System.out.println("Creaci�n de suspension al socio:" + suspension.getSocio().getNombre() + " " + suspension.getSocio().getApellido() + ", de motivo: " + suspension.getMotivo() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(suspension) de SuspensionDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Suspension suspension){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(suspension);
			tr.commit();
			System.out.println("Actualizaci�n de suspension exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(suspension) de SuspensionDAO");
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
			em.remove(SuspensionDAO.find(id));
			tr.commit();
			System.out.println("Eliminaci�n de suspension exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de SuspensionDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
