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
			
		}catch(Exception e){
			//TODO exception
		}
		
		request.setAttribute("resultados", resultados);
	}

	public static void searchTemas(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	public static void searchIsbn(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	public static void searchAutor(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	public static void searchEditorial(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}



	

}
