package org.proyectoBiblioteca.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.proyectoBiblioteca.dao.AutorDAO;
import org.proyectoBiblioteca.dao.EditorialDAO;
import org.proyectoBiblioteca.dao.LibroDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Autor;
import org.proyectoBiblioteca.domain.Editorial;
import org.proyectoBiblioteca.domain.Libro;

public class LibroService {

	public static void retrieveAll(HttpServletRequest request) {
		
		//ejecuto una consulta para traer los libros activas y los mando como atributo en la request
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		List<Libro> libros = null;
		
		try{
			
			TypedQuery<Libro> query = em.createNamedQuery("Libro.findAllActive",Libro.class);
			
			if(!query.getResultList().isEmpty()){
	
				libros = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("libros", libros);
		
	}

	public static void delete(HttpServletRequest request) {
		//hago la baja lógica del libro
		Libro libro = null;
		
		try{
			libro = LibroDAO.find(Long.parseLong(request.getParameter("id")));
			
			libro.setActivo(false);
			libro.setFechaBaja(new Date());
			//TODO implementar tema de motivo baja
			LibroDAO.update(libro);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		//TODO revisar este método, ver si aviso o no cuando tengo éxito
	}
	
	public static void retrieveById(HttpServletRequest request){
		
		Libro libro = null;
		
		try{
			libro = LibroDAO.find(Long.parseLong(request.getParameter("id")));
			
			request.setAttribute("libro", libro);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}

	public static void saveLibro(HttpServletRequest request) {

		Libro libro = null;
		
		//Si es nuevo lo creo, sino lo obtengo
		
		String parameterId = request.getParameter("id");
		
		if ((parameterId.equals("")) || (parameterId == null)){
			
			libro = new Libro();
			libro.setFechaAlta(new Date());

		}else{
			
			libro = LibroDAO.find(Long.parseLong(parameterId));
		}
		
		//seteo atributos con los parámetros

		libro.setTitulo(request.getParameter("titulo"));
		libro.setEtiquetas(request.getParameter("etiquetas"));
		libro.setIsbn(request.getParameter("isbn"));
		libro.setPaisOrigen(request.getParameter("paisOrigen"));
		libro.setRango(Integer.parseInt(request.getParameter("rango")));
		libro.setEditorial(EditorialDAO.find(Long.parseLong(request.getParameter("editorial"))));
		
		//TODO cambiar para varios autores! También cambiar el jsp de nuevo libro
		List<Autor> autores = new ArrayList<Autor>();
	
		try{
			autores.add(AutorDAO.find(Long.parseLong(request.getParameter("autores"))));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		libro.setAutores(autores);

		
		try{
			LibroDAO.update(libro);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}

		
	}

	public static void retrieveData(HttpServletRequest request) {
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		try{

			TypedQuery<Editorial> queryEditoriales = em.createNamedQuery("Editorial.findAllActive",Editorial.class);
			
			TypedQuery<Autor> queryAutores = em.createNamedQuery("Autor.findAllActive",Autor.class);
			
			if(!queryEditoriales.getResultList().isEmpty()){
	
				request.setAttribute("editoriales",queryEditoriales.getResultList());
				request.setAttribute("autores",queryAutores.getResultList());
			}

		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}
}
