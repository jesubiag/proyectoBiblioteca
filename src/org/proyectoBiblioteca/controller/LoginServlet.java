package org.proyectoBiblioteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.proyectoBiblioteca.service.LoginService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String action = request.getParameter("action");
		
		if (action.equals("terminate")){
			//El usuario solicita desloguearse
			LoginService.closeSession(request);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//la lógica de logueo está en el serivicio de login (LoginService) que devuelve un string con la pantalla para el forward
		request.getRequestDispatcher(LoginService.validateLoginData(request,session)).forward(request,response);
		
	}

}
