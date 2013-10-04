package org.proyectoBiblioteca.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.proyectoBiblioteca.domain.Usuario;

public class UsuarioDAO {
	
	public static Usuario find(long id){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Usuario usuario = null;
		
		try{
			usuario = em.find(Usuario.class, id);
		}
		catch(Exception ex){
			System.out.println("Error en find(id) de UsuarioDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return usuario;
		
	}

	public static void create(Usuario usuario){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.persist(usuario);
			tr.commit();
			System.out.println("Creación de usuario " + usuario.getUsuario() + " exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en create(usuario) de UsuarioDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
	public static void update(Usuario usuario){
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try{
			em.merge(usuario);
			tr.commit();
			System.out.println("Actualización de usuario exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en update(usuario) de UsuarioDAO");
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
			em.remove(UsuarioDAO.find(id));
			tr.commit();
			System.out.println("Eliminación de usuario exitosa");
		}
		catch(Exception ex){
			tr.rollback();
			System.out.println("Error en delete(id) de UsuarioDAO");
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
	}
	
}
