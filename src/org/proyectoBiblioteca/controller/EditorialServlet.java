package org.proyectoBiblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.EditorialService;

public class EditorialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditorialServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		if (action == null && (request.getSession().getAttribute("user") != null) ){
			//voy a pantalla de editoriales
			//TODO ver cómo implementar paginación para no traer todo de una
			//TODO hacer una búsqueda de editoriales en el jsp
			EditorialService.retrieveAll(request);
			request.getRequestDispatcher("/editoriales.jsp").forward(request, response);
			
		}else if(action.equals("new")){
			//redirecciono a pantalla de mod. autor con campos limpios
			request.getRequestDispatcher("/nuevaEditorial.jsp").forward(request,response);
			
		}else if(action.equals("delete")){
			//intento eliminar el autor con el parámetro id
			EditorialService.delete(request);
			response.sendRedirect("/proyectoBiblioteca/Editoriales");
			
		}else if(action.equals("edit")){
			//tomo par. id y redirecciono a mod. socio con los datos de ese socio
			EditorialService.retrieveById(request);
			request.getRequestDispatcher("/nuevaEditorial.jsp").forward(request,response);
		}else{
			response.sendRedirect("/proyectoBiblioteca/Editoriales");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EditorialService.saveEditorial(request);
		response.sendRedirect("/proyectoBiblioteca/Editoriales");
		
	}

}
