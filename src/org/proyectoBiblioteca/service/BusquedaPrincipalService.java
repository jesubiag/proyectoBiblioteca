package org.proyectoBiblioteca.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Autor;
import org.proyectoBiblioteca.domain.Libro;

public class BusquedaPrincipalService {

	public static void searchLibro(HttpServletRequest request) {

		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		List<Libro> resultados = null;
		
		try{
			
			TypedQuery<Libro> query = entityManager.createNamedQuery("Libro.findByTitle",Libro.class);
			
			query.setParameter("titulo", "%" + request.getParameter("search").trim() + "%");
			
			if(!query.getResultList().isEmpty()){
				resultados = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			entityManager.close();
		}
		
		request.setAttribute("resultados", resultados);
	}

	public static void searchIsbn(HttpServletRequest request) {

		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		List<Libro> resultados = null;
		
		try{
			
			TypedQuery<Libro> query = entityManager.createNamedQuery("Libro.findByIsbn",Libro.class);
			
			query.setParameter("isbn", "%" + request.getParameter("search").trim() + "%");
			
			if(!query.getResultList().isEmpty()){
				resultados = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			entityManager.close();
		}
		
		request.setAttribute("resultados", resultados);		
		
	}

	public static void searchAutor(HttpServletRequest request) {

		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		Autor autor = null;
		
		List<Libro> resultados = null;
		
		try{
			TypedQuery<Autor> query = entityManager.createNamedQuery("Autor.findByName",Autor.class);
			query.setParameter("nombre", request.getParameter("search").trim());
			
			if(!query.getResultList().isEmpty()){
				autor = query.getResultList().get(0);
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		if(null != autor){
			
			try{
				TypedQuery<Libro> query = entityManager.createNamedQuery("Libro.findByAuthor",Libro.class);
				query.setParameter("autor", autor);
				
				if(!query.getResultList().isEmpty()){
					resultados = query.getResultList();
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				entityManager.close();
			}
			
		}
		
		request.setAttribute("resultados", resultados);
		
	}

	public static void searchEditorial(HttpServletRequest request) {

		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		List<Libro> resultados = null;
		
		try{
			
			TypedQuery<Libro> query = entityManager.createNamedQuery("Libro.findByEditorial",Libro.class);
			
			query.setParameter("editorial", "%" + request.getParameter("search").trim() + "%");
			
			if(!query.getResultList().isEmpty()){
				resultados = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			entityManager.close();
		}
		
		request.setAttribute("resultados", resultados);
		
	}
	
	public static void searchTemas(HttpServletRequest request) {
		
		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		List<Libro> resultados = null;
		
		try{
			TypedQuery<Libro> query = entityManager.createNamedQuery("Libro.findByTag",Libro.class);
			
			query.setParameter("etiqueta", request.getParameter("search").trim());
			
			if(!query.getResultList().isEmpty()){
				resultados = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			entityManager.close();
		}
		
		request.setAttribute("resultados", resultados);
	}



	

}
