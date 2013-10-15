package org.proyectoBiblioteca.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.LibroService;

public class LibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibroServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		if (action == null && (request.getSession().getAttribute("user") != null) ){
			//voy a pantalla de libros y ejemplares
			//TODO ver cómo implementar paginación para no traer todo de una
			//TODO hacer una búsqueda de libros en el jsp
			LibroService.retrieveAll(request);
			request.getRequestDispatcher("/libros.jsp").forward(request, response);
			
		}else if(action.equals("new")){
			//redirecciono a pantalla de mod. libro con campos limpios
			LibroService.retrieveData(request);
			request.getRequestDispatcher("/nuevoLibro.jsp").forward(request,response);
			
		}else if(action.equals("delete")){
			//intento eliminar el libro con el parámetro id
			LibroService.delete(request);
			response.sendRedirect("/proyectoBiblioteca/Libros");
			
		}else if(action.equals("edit")){
			//tomo par. id y redirecciono a mod. libro con los datos de ese socio
			LibroService.retrieveData(request);
			LibroService.retrieveById(request);
			request.getRequestDispatcher("/nuevoLibro.jsp").forward(request,response);
		}else{
			response.sendRedirect("/proyectoBiblioteca/Libros");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LibroService.saveLibro(request);
		response.sendRedirect("/proyectoBiblioteca/Libros");
		
	}

}
