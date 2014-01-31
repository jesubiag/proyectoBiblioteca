package org.proyectoBiblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.SocioService;

@WebServlet(name="SocioServlet", urlPatterns="/Socios")
public class SocioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public SocioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		if (null == action && null != request.getSession().getAttribute("user")){
			//voy a pantalla de socios
			//TODO ver cómo implementar paginación para no traer todo de una
			//TODO hacer una búsqueda de socios en el jsp
			SocioService.retrieveAll(request);
			request.getRequestDispatcher("/socios.jsp").forward(request, response);
			
		}else if("new".equals(action)){
			//redirecciono a pantalla de mod. socio con campos limpios
			SocioService.retrieveData(request);
			request.getRequestDispatcher("/nuevoSocio.jsp").forward(request,response);
			
		}else if("delete".equals(action)){
			//intento eliminar el socio con el parámetro id
			response.sendRedirect(SocioService.delete(request));
			
		}else if("edit".equals(action)){
			//tomo par. id y redirecciono a mod. socio con los datos de ese socio
			SocioService.retrieveById(request);
			SocioService.retrieveData(request);
			request.getRequestDispatcher("/nuevoSocio.jsp").forward(request,response);
		}else{
			response.sendRedirect("/proyectoBiblioteca/Socios");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SocioService.saveSocio(request);
		response.sendRedirect("/proyectoBiblioteca/");
		
	}

}
