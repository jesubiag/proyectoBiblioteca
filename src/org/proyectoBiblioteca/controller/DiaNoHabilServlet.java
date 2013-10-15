package org.proyectoBiblioteca.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.DiaNoHabilService;

public class DiaNoHabilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DiaNoHabilServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		if (action == null && (request.getSession().getAttribute("user") != null) ){
			//voy a pantalla de d�as no h�biles
			//TODO ver c�mo implementar paginaci�n para no traer todo de una
			//TODO hacer una b�squeda de d�as no h�biles en el jsp quiz�s por a�o
			DiaNoHabilService.retrieveAll(request);
			request.getRequestDispatcher("/diasNoHabiles.jsp").forward(request, response);
			
		}else if(action.equals("new")){
			//redirecciono a pantalla de mod. autor con campos limpios
			request.getRequestDispatcher("/nuevoDiaNoHabil.jsp").forward(request,response);
			
		}else if(action.equals("delete")){
			//intento eliminar el autor con el par�metro id
			DiaNoHabilService.delete(request);
			response.sendRedirect("/proyectoBiblioteca/DiasNoHabiles");
			
		}else if(action.equals("edit")){
			//tomo par. id y redirecciono a mod. socio con los datos de ese socio
			DiaNoHabilService.retrieveById(request);
			request.getRequestDispatcher("/nuevoDiaNoHabil.jsp").forward(request,response);
		}else{
			response.sendRedirect("/proyectoBiblioteca/DiasNoHabiles");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DiaNoHabilService.saveDiaNoHabil(request);
		response.sendRedirect("/proyectoBiblioteca/DiasNoHabiles");
		
	}

}