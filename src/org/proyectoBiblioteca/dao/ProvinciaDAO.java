package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Provincia;

public class ProvinciaDAO {

	public static Provincia find(String nombre){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Provincia provincia = null;
		
		try{
			provincia = entityManager.find(Provincia.class, nombre);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de ProvinciaDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
		return provincia;
		
	}

	public static void create(Provincia provincia){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.persist(provincia);
			tr.commit();
			System.out.println("Creación de provincia:" + provincia.getNombre() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(provincia) de ProvinciaDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Provincia provincia){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.merge(provincia);
			tr.commit();
			System.out.println("Actualización de provincia exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(provincia) de ProvinciaDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void delete(String nombre){

		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		
		tr.begin();
		
		try{
			entityManager.remove(ProvinciaDAO.find(nombre));
			tr.commit();
			System.out.println("Eliminación de provincia exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de ProvinciaDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
