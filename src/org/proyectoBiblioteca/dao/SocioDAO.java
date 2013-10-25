package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Socio;

public class SocioDAO {

	public static Socio find(long id){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Socio socio = null;
		
		try{
			socio = entityManager.find(Socio.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de SocioDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
		return socio;
		
	}

	public static void create(Socio socio){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(socio);
			tr.commit();
			System.out.println("Creaci�n de socio " + socio.getNombre() + " " + socio.getApellido() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(socio) de SocioDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Socio socio){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(socio);
			tr.commit();
			System.out.println("Actualizaci�n de socio exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(socio) de SocioDAO");
			ex.printStackTrace();
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
			entityManager.remove(entityManager.find(Socio.class, id)); //TODO problema al intentar borrar la entidad, la encuentra bien pero no puede borrarla. Revisar relaciones!
			tr.commit();
			System.out.println("Eliminaci�n de socio exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de SocioDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
