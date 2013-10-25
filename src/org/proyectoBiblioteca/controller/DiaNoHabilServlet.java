package org.proyectoBiblioteca.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.DiaNoHabilService;

@WebServlet(name="DiaNoHabilServlet", urlPatterns="/DiasNoHabiles")
public class DiaNoHabilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DiaNoHabilServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		if (null == action && null != request.getSession().getAttribute("user")){
			//voy a pantalla de días no hábiles
			//TODO ver cómo implementar paginación para no traer todo de una
			//TODO hacer una búsqueda de días no hábiles en el jsp quizás por año
			DiaNoHabilService.retrieveAll(request);
			request.getRequestDispatcher("/diasNoHabiles.jsp").forward(request, response);
			
		}else if("new".equals(action)){
			//redirecciono a pantalla de mod. autor con campos limpios
			request.getRequestDispatcher("/nuevoDiaNoHabil.jsp").forward(request,response);
			
		}else if("delete".equals(action)){
			//intento eliminar el autor con el parámetro id
			DiaNoHabilService.delete(request);
			response.sendRedirect("/proyectoBiblioteca/DiasNoHabiles");
			
		}else if("edit".equals(action)){
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
