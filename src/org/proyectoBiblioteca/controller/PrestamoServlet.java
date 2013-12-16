package org.proyectoBiblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.PrestamoService;

@WebServlet("/Prestamos")
public class PrestamoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public PrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if("new".equals(action)){
			//se pide la creación de un nuevo préstamo
			PrestamoService.retrieveData(request);//llamo al serivico para que traiga los datos necesarios para el préstamo
			request.getRequestDispatcher("/nuevoPrestamo.jsp").forward(request, response);
		}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//redirecciono a la pantalla de detalle para el libro prestado
		response.sendRedirect(PrestamoService.save(request,request.getSession()));
	}
	
}
