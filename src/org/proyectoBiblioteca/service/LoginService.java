package org.proyectoBiblioteca.service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Usuario;

public class LoginService {

	public static String validateLoginData(HttpServletRequest request, HttpSession session){
		
		String ret = null;
		Usuario user = null;
		
		String usuario = request.getParameter("user");
		String clave = request.getParameter("password");
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		try{
			//query original : TypedQuery<Usuario> query = em.createQuery("Select usr From Usuario adm Where usr.usuario = '" + usuario + "'",Usuario.class);
			
			TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUser",Usuario.class);
			
			query.setParameter("usuario", usuario);
			
			if(!query.getResultList().isEmpty()){
				
				if(query.getResultList().size() > 1){
					//trajo más de un resultado, error muy grave.
					//TODO reportar error quizás tirando una exception
				}
				
				user = query.getResultList().get(0);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		if(user == null){
			//no está en la base - Redirecciono al index agregando el atributo loginError para que el jsp lo sepa e informe
			ret = "index.jsp";
			request.setAttribute("loginError","Error en inicio de sesión. Verifique su nombre de usuario e intente nuevamente.");
			
		}else{
			//Existe, reviso pass
			if (clave.equals(user.getClave())){
				//Clave correcta - mando bean de usuario como atributo y redirecciono a menu.jsp (el tipo de usuario está en el bean de usuario =) )
					session.setAttribute("user", user);	
					ret = "menu.jsp";
			}else{
					//contraseña incorrecta - Redirecciono al index agregando el atributo loginError para que el jsp lo sepa e informe
					ret = "index.jsp";
					request.setAttribute("loginError","Error en inicio de sesión. Contraseña incorrecta. Intente nuevamente.");
			}
		}
		
		return ret;
		
	}

	public static void closeSession(HttpServletRequest request) {

		HttpSession session = request.getSession();
		
		if(session != null){
			
			session.invalidate();
			
		}
	}

}
