package org.proyectoBiblioteca.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.DiaNoHabil;

public class DiaNoHabilDAO {

	public static DiaNoHabil find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		DiaNoHabil diaNoHabil = null;
		
		try{
			diaNoHabil = em.find(DiaNoHabil.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de DiaNoHabilDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return diaNoHabil;
		
	}

	public static void create(DiaNoHabil diaNoHabil){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(diaNoHabil);
			tr.commit();
			System.out.println("Creación de diaNoHabil:" + diaNoHabil.getFecha() + ", de motivo: " + diaNoHabil.getMotivo() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(diaNoHabil) de DiaNoHabilDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(DiaNoHabil diaNoHabil){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(diaNoHabil);
			tr.commit();
			System.out.println("Actualización de diaNoHabil exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(diaNoHabil) de DiaNoHabilDAO");
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
			em.remove(DiaNoHabilDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de diaNoHabil exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de DiaNoHabilDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
