package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Localidad;

public class LocalidadDAO {

	public static Localidad find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Localidad localidad = null;
		
		try{
			localidad = em.find(Localidad.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de LocalidadDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return localidad;
		
	}

	public static void create(Localidad localidad){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(localidad);
			tr.commit();
			System.out.println("Creación de localidad:" + localidad.getNombre() + ", de la prov: " + localidad.getProvincia().getNombre() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(localidad) de LocalidadDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Localidad localidad){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(localidad);
			tr.commit();
			System.out.println("Actualización de localidad exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(localidad) de LocalidadDAO");
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
			em.remove(LocalidadDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de localidad exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de LocalidadDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}

	public static Localidad findByFields(String provincia, String localidad) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
