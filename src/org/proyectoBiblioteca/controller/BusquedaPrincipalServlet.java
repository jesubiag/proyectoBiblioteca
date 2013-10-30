package org.proyectoBiblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.proyectoBiblioteca.service.BusquedaPrincipalService;
import org.proyectoBiblioteca.service.LibroService;

@WebServlet("/Busqueda")
public class BusquedaPrincipalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BusquedaPrincipalServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchType = request.getParameter("type");
		
		if("libro".equals(searchType)){
			//búsqueda por libro
			BusquedaPrincipalService.searchLibro(request);
			request.getRequestDispatcher("/resultados.jsp").forward(request,response);
			
		}else if("temas".equals(searchType)){
			//búsqueda por temas
			BusquedaPrincipalService.searchTemas(request);
			request.getRequestDispatcher("/resultados.jsp").forward(request,response);
		
		}else if("isbn".equals(searchType)){
			//búsqueda por isbn
			BusquedaPrincipalService.searchIsbn(request);
			request.getRequestDispatcher("/resultados.jsp").forward(request,response);
			
		}else if("autor".equals(searchType)){
			//búsqueda por autor
			BusquedaPrincipalService.searchAutor(request);
			request.getRequestDispatcher("/resultados.jsp").forward(request,response);
			
		}else if("editorial".equals(searchType)){
			//búsqueda por editorial
			BusquedaPrincipalService.searchEditorial(request);
			request.getRequestDispatcher("/resultados.jsp").forward(request,response);
		
		}else if("detalleLibro".equals(searchType)){
			//voy a pantalla de detalle de libro
			LibroService.retrieveById(request);
			request.getRequestDispatcher("/detalleLibro.jsp").forward(request, response);
				
		}else{
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
