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
			SocioService.retrieveAll(request);
			request.getRequestDispatcher("/socios.jsp").forward(request, response);
		}else{
			//Si no es null, veo si es una acción del abm
			//TODO completar lógica del abm
			switch(action){
			case "new"://redirecciono a pantalla de mod. usuario con campos vacíos
				break;
			case "delete"://tomo par id e intento eliminar de la db
				break;
			case "update"://tomo par id y redirecciono a pantalla de mod. usuario con ese usuario cargado
				break;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
