package org.proyectoBiblioteca.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.proyectoBiblioteca.dao.PersistenceManager;
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
		}
		
		request.setAttribute("resultados", resultados);		
		
	}

	public static void searchAutor(HttpServletRequest request) {

		EntityManager entityManager = PersistenceManager.getEntityManager();
		
		List<Libro> resultados = null;
		
		try{
			//TODO revisar query, hay que ver si es posible o hay que parsear lista de autores
			TypedQuery<Libro> query = entityManager.createNamedQuery("Libro.findByAuthor",Libro.class);
			
			query.setParameter("autor", "%" + request.getParameter("search").trim() + "%");
			
			if(!query.getResultList().isEmpty()){
				resultados = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
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
		}
		
		request.setAttribute("resultados", resultados);
		
	}
	
	public static void searchTemas(HttpServletRequest request) {
		// TODO Usar api de criteria para hacer la query
		//crear query - parsear listado por comas - agregar parámetros uno por uno - devolver resultado
	}



	

}
