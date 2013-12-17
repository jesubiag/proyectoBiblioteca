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
		}else if("return".equals(action)){
			//se pide la devolución de un ejemplar
			PrestamoService.retrieveData(request);//llamo al serivico para que traiga los datos necesarios para la devolución
			request.getRequestDispatcher("/devolucion.jsp").forward(request, response);
		}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		
		if("lend".equals(action)){
			
			//creo el préstamo y redirecciono a la pantalla de detalle para el libro prestado
			response.sendRedirect(PrestamoService.saveLoan(request,request.getSession()));
		
		}else if("return".equals(action)){
			
			//realizo la devolución y redirecciono a la pantalla de detalle para el libro devuelto
			response.sendRedirect(PrestamoService.saveReturn(request,request.getSession()));
		}
		
	}
	
}
