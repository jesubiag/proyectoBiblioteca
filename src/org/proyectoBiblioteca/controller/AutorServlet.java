package org.proyectoBiblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.AutorService;

public class AutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AutorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		if (action == null && (request.getSession().getAttribute("user") != null) ){
			//voy a pantalla de autores
			//TODO ver cómo implementar paginación para no traer todo de una
			//TODO hacer una búsqueda de autores en el jsp
			AutorService.retrieveAll(request);
			request.getRequestDispatcher("/autores.jsp").forward(request, response);
			
		}else if(action.equals("new")){
			//redirecciono a pantalla de mod. autor con campos limpios
			request.getRequestDispatcher("/nuevoAutor.jsp").forward(request,response);
			
		}else if(action.equals("delete")){
			//intento eliminar el autor con el parámetro id
			AutorService.delete(request);
			response.sendRedirect("/proyectoBiblioteca/Autores");
			
		}else if(action.equals("edit")){
			//tomo par. id y redirecciono a mod. socio con los datos de ese socio
			AutorService.retrieveById(request);
			request.getRequestDispatcher("/nuevoAutor.jsp").forward(request,response);
		}else{
			response.sendRedirect("/proyectoBiblioteca/Autores");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AutorService.saveAutor(request);
		response.sendRedirect("/proyectoBiblioteca/Autores");
		
	}

}
