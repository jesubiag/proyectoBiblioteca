package org.proyectoBiblioteca.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.proyectoBiblioteca.dao.AutorDAO;
import org.proyectoBiblioteca.dao.EditorialDAO;
import org.proyectoBiblioteca.dao.LibroDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Autor;
import org.proyectoBiblioteca.domain.Editorial;
import org.proyectoBiblioteca.domain.Ejemplar;
import org.proyectoBiblioteca.domain.Libro;
import org.proyectoBiblioteca.enums.EstadoEjemplar;

public class LibroService {

	public static void retrieveAll(HttpServletRequest request) {
		
		//ejecuto una consulta para traer los libros activas y los mando como atributo en la request
		
		EntityManager em = PersistenceManager.getEntityManager();
		
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
		HttpSession session = request.getSession();
		Libro libro = null;
		int contador = 0;
		try{
			libro = LibroDAO.find(Long.parseLong(request.getParameter("id")));
			
			List<Ejemplar> ejemplares = libro.getEjemplares();
			
			//reviso que no haya ejemplares prestamos
			for(Ejemplar ejemplar : ejemplares){
			
				if(ejemplar.getEstado() == EstadoEjemplar.prestado){
					++contador;
				}
			}
			
			if(contador > 0){
				//el libro posee ejemplares prestados, cancelo la baja
				session.setAttribute("mensajeLibro", "Baja de libro no realizada. Este libro todavía posee ejemplares prestados.");
			}else{
				
				libro.setActivo(false);
				libro.setFechaBaja(new Date());
				
				LibroDAO.update(libro);
				
				session.setAttribute("mensajeLibro", "Baja de libro realizada con éxito.");	
			}
			
			
		}catch(NumberFormatException ex){
			session.setAttribute("mensajeLibro", "Baja de libro no realizada. Error al intentar realizar la baja. Intente nuevamente.");
		}
		
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

		HttpSession session = request.getSession();
		Libro libro = null;
		
		//Si es nuevo lo creo, sino lo obtengo
		
		String parameterId = request.getParameter("id");
		
		if ("".equals(parameterId) || null == parameterId){
			
			libro = new Libro();
			libro.setFechaAlta(new Date());

		}else{
			
			libro = LibroDAO.find(Long.parseLong(parameterId));
		}
		
		//seteo atributos con los parámetros

		libro.setTitulo(request.getParameter("titulo"));
		libro.setIsbn(request.getParameter("isbn"));
		libro.setPaisOrigen(request.getParameter("paisOrigen"));
		libro.setRango(Integer.parseInt(request.getParameter("rango")));
		libro.setEditorial(EditorialDAO.find(Long.parseLong(request.getParameter("editorial"))));
		libro.setDescripcion(request.getParameter("descripcion"));
		libro.setLinkImagen(request.getParameter("linkImagen"));
		
		//recibo el campo etiquetas que es una cadena separada por , o por ; y lo parseo para armar una lista de etiquetas individuales 
		String[] etiquetas = request.getParameter("etiquetas").split(",|;");
		
		libro.setEtiquetas(new ArrayList<String>(Arrays.asList(etiquetas)));
		
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
			session.setAttribute("mensajeLibro", "Alta/Mod. de libro realizada con éxito.");
			
		}catch(NumberFormatException ex){
			session.setAttribute("mensajeLibro", "Alta/Mod. de libro no realizada. Ha ocurrido un error al intentar dar de alta o modificar el libro. Intente nuevamente.");
		}

		
	}

	public static void retrieveData(HttpServletRequest request) {
		
		EntityManager em = PersistenceManager.getEntityManager();
		
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
