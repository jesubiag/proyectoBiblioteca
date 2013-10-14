package org.proyectoBiblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.SocioService;

public class SocioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public SocioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		if (action == null && (request.getSession().getAttribute("user") != null) ){
			//voy a pantalla de socios
			//TODO ver cómo implementar paginación para no traer todo de una
			//TODO hacer una búsqueda de socios en el jsp
			SocioService.retrieveAll(request);
			request.getRequestDispatcher("/socios.jsp").forward(request, response);
			
		}else if(action.equals("new")){
			//redirecciono a pantalla de mod. socio con campos limpios
			SocioService.retrieveStatesList(request);
			request.getRequestDispatcher("/nuevoSocio.jsp").forward(request,response);
			
		}else if(action.equals("delete")){
			//intento eliminar el socio con el parámetro id
			SocioService.delete(request);
			response.sendRedirect("/proyectoBiblioteca/Socios");
			
		}else if(action.equals("edit")){
			//tomo par. id y redirecciono a mod. socio con los datos de ese socio
			SocioService.retrieveById(request);
			SocioService.retrieveStatesList(request);
			request.getRequestDispatcher("/nuevoSocio.jsp").forward(request,response);
		}else{
			response.sendRedirect("/proyectoBiblioteca/Socios");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SocioService.saveSocio(request);
		response.sendRedirect("/proyectoBiblioteca/Socios");
		
	}

}
