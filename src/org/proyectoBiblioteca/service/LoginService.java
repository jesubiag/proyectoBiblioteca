package org.proyectoBiblioteca.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Usuario;

public class LoginService {

	public static String validateLoginData(String usuario,String clave, HttpSession session, HttpServletRequest request){
		
		String ret = null;
		Usuario user = null;
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		try{
			//TODO pasar esto al dao
			TypedQuery<Usuario> query = em.createQuery("Select usr From Usuario adm Where usr.usuario = '" + usuario + "'",Usuario.class);
			
			if(!query.getResultList().isEmpty()){
				
				if(query.getResultList().size() > 1){
					//trajo más de un resultado, error muy grave.
					//TODO reportar error quizás tirando una exception
				}
				
				user = query.getResultList().get(0);
				session.setAttribute("user", user);
			}
			
		}catch(Exception ex){
			//Error en la query :/ TODO armar captura de excepciones
		}
		
		if(user == null){
			//no está en la base - Redirecciono al index agregando el atributo loginError para que el jsp lo sepa e informe
			ret = "jsp/index.jsp";
			request.setAttribute("loginError","Error en inicio de sesión. Verifique su nombre de usuario e intente nuevamente.");
			
		}else{
			//Existe, reviso pass
			if (clave.equals(user.getClave())){
				//Clave correcta - redirecciono a menu.jsp (el tipo de usuario está en el bean de usuario =) )
					ret = "jsp/success.jsp";
			}else{
					//contraseña incorrecta - Redirecciono al index agregando el atributo loginError para que el jsp lo sepa e informe
					ret = "jsp/index.jsp";
					request.setAttribute("loginError","Error en inicio de sesión. Contraseña incorrecta. Intente nuevamente.");
			}
		}
		
		return ret;
		
	}

}
