package org.proyectoBiblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.EditorialService;

@WebServlet(name="EditorialServlet", urlPatterns="/Editoriales")
public class EditorialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditorialServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
		if (null == action && null != request.getSession().getAttribute("user") ){
			//voy a pantalla de editoriales
			EditorialService.retrieveAll(request);
			request.getRequestDispatcher("/editoriales.jsp").forward(request, response);
			
		}else if("new".equals(action)){
			//redirecciono a pantalla de mod. autor con campos limpios
			request.getRequestDispatcher("/nuevaEditorial.jsp").forward(request,response);
			
		}else if("delete".equals(action)){
			//intento eliminar el autor con el parámetro id
			EditorialService.delete(request);
			response.sendRedirect("/proyectoBiblioteca/");
			
		}else if("edit".equals(action)){
			//tomo par. id y redirecciono a mod. socio con los datos de ese socio
			EditorialService.retrieveById(request);
			request.getRequestDispatcher("/nuevaEditorial.jsp").forward(request,response);
		}else{
			response.sendRedirect("/proyectoBiblioteca/Editoriales");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EditorialService.saveEditorial(request);
		response.sendRedirect("/proyectoBiblioteca/");
		
	}

}
