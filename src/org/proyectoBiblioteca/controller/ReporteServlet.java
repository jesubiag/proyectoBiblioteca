package org.proyectoBiblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectoBiblioteca.service.ReporteService;

@WebServlet("/Reportes")
public class ReporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReporteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String report = request.getParameter("report");
		
		if("retirosMesAMes".equals(report)){
			ReporteService.retirosMesAMes(request);
			request.getRequestDispatcher("/reporteRetiros.jsp").forward(request, response);
			
		}else if("devolucionesEnMora".equals(report)){
			ReporteService.devolucionesEnMora(request);
			request.getRequestDispatcher("/reporteDevolucionesMora.jsp").forward(request, response);
			
		}else if("librosMasRetirados".equals(report)){
			ReporteService.librosMasRetirados(request);
			request.getRequestDispatcher("/reporteMasRetirados.jsp").forward(request, response);

		}else{
			request.getRequestDispatcher("/reportes.jsp").forward(request, response);
		}
		
		
	}

}
