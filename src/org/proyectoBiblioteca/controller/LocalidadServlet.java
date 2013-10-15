package org.proyectoBiblioteca.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.LocalidadService;

public class LocalidadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LocalidadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		if (action == null && (request.getSession().getAttribute("user") != null) ){
			//voy a pantalla de localidades
			//TODO ver cómo implementar paginación para no traer todo de una
			//TODO hacer una búsqueda de localidades en el jsp
			LocalidadService.retrieveAll(request);
			request.getRequestDispatcher("/localidades.jsp").forward(request, response);
			
		}else if(action.equals("new")){
			//redirecciono a pantalla de mod. socio con campos limpios
			LocalidadService.retrieveData(request);
			request.getRequestDispatcher("/nuevaLocalidad.jsp").forward(request,response);
			
		}else if(action.equals("delete")){
			//intento eliminar el socio con el parámetro id
			LocalidadService.delete(request);
			response.sendRedirect("/proyectoBiblioteca/Localidades");
			
		}else if(action.equals("edit")){
			//tomo par. id y redirecciono a mod. socio con los datos de ese socio
			LocalidadService.retrieveById(request);
			LocalidadService.retrieveData(request);
			request.getRequestDispatcher("/nuevaLocalidad.jsp").forward(request,response);
		}else{
			response.sendRedirect("/proyectoBiblioteca/Localidades");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LocalidadService.saveLocalidad(request);
		response.sendRedirect("/proyectoBiblioteca/Localidades");
		
	}

}
