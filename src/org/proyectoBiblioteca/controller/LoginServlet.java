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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = request.getParameter("user");
		String clave = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		//la lógica de logueo está en el serivicio de login (LoginService) que devuelve un string con la pantalla para el forward
		request.getRequestDispatcher(LoginService.validateLoginData(usuario,clave,session,request)).forward(request,response);
		
	}

}
