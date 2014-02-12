package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Usuario;

public class UsuarioDAO {
	
	public static Usuario find(long l){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Usuario usuario = null;
		
		try{
			usuario = entityManager.find(Usuario.class, l);
		}
		catch(Exception ex){
			System.out.println("Error en find(usuario) de UsuarioDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
		return usuario;
		
	}

	public static void create(Usuario usuario){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		tr.begin();
		
		try{
			entityManager.persist(usuario);
			tr.commit();
			System.out.println("Creación de usuario " + usuario.getUsuario() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(usuario) de UsuarioDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void update(Usuario usuario){
		
		EntityManager entityManager = PersistenceManager.getEntityManager();

		EntityTransaction tr = entityManager.getTransaction();
		tr.begin();
		
		try{
			entityManager.merge(usuario);
			tr.commit();
			System.out.println("Actualización de usuario exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(usuario) de UsuarioDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public static void delete(Long id){

		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		EntityTransaction tr = entityManager.getTransaction();
		tr.begin();
		
		try{
			entityManager.remove(entityManager.merge(UsuarioDAO.find(id)));
			tr.commit();
			System.out.println("Eliminación de usuario exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de UsuarioDAO");
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
