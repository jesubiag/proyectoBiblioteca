package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.proyectoBiblioteca.domain.Localidad;

public class LocalidadDAO {

	public static Localidad find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Localidad localidad = null;
		
		try{
			localidad = entityManager.find(Localidad.class, id);
		}
		catch(Exception ex){
			System.err.println("Error en LocalidadDAO.find" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
		return localidad;
		
	}

	public static void create(Localidad localidad){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(localidad);
			tr.commit();
			System.out.println("Creación de localidad:" + localidad.getNombre() + ", de la prov: " + localidad.getProvincia().getNombre() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en LibroDAO.create" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Localidad localidad){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(localidad);
			tr.commit();
			System.out.println("Actualización de localidad exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en LibroDAO.update" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void delete(long id){

		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.remove(LocalidadDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de localidad exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.err.println("Error en LibroDAO.delete" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
		}
		finally {
			entityManager.close();
		}
		
	}

	public static Localidad findByFields(String nombreProvincia, String nombreLocalidad) {
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Localidad localidad = null;
		
		try{
			
			TypedQuery<Localidad> query = entityManager.createNamedQuery("Localidad.findByFields",Localidad.class);
			
			query.setParameter("localidad", nombreLocalidad);
			query.setParameter("provincia", nombreProvincia);
			
			if( (!query.getResultList().isEmpty()) && (query.getResultList().size()<2)){
	
				localidad = query.getResultList().get(1);
			}
			
		}catch(Exception ex){
			System.err.println("Error en LibroDAO.findByFields" + "(" + Thread.currentThread().getStackTrace()[1].getLineNumber() + "):" + ex.getLocalizedMessage());
			
		}finally{
			entityManager.close();
		}
		
		return localidad;
	}
	
}
