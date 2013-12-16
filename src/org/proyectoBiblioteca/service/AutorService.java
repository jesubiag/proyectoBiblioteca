package org.proyectoBiblioteca.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.proyectoBiblioteca.dao.AutorDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Autor;

public class AutorService {

	public static void retrieveAll(HttpServletRequest request) {
		
		//ejecuto una consulta para traer los autores activos y los mando como atributo en la request
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		List<Autor> autores = null;
		
		try{
			
			TypedQuery<Autor> query = em.createNamedQuery("Autor.findAllActive",Autor.class);
			
			if(!query.getResultList().isEmpty()){
	
				autores = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("autores", autores);
		
	}

	public static void delete(HttpServletRequest request) {
		//hago la baja lógica del autor
		Autor autor = null;
		
		try{
			autor = AutorDAO.find(Long.parseLong(request.getParameter("id")));
			
			autor.setActivo(false);
			autor.setFechaBaja(new Date());
			
			AutorDAO.update(autor);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		//TODO revisar este método, ver si aviso o no cuando tengo éxito
	}
	
	public static void retrieveById(HttpServletRequest request){
		
		Autor autor = null;
		
		try{
			autor = AutorDAO.find(Long.parseLong(request.getParameter("id")));
			
			request.setAttribute("autor", autor);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}

	public static void saveAutor(HttpServletRequest request) {

		Autor autor = null;
		
		//Si es nuevo lo creo, sino lo obtengo
		
		final String parameterId = request.getParameter("id");
		
		if ("".equals(parameterId) || null == parameterId){
			
			autor = new Autor();
			autor.setFechaAlta(new Date());

		}else{
			
			autor = AutorDAO.find(Long.parseLong(parameterId));
		}
		
		//seteo atributos con los parámetros

		autor.setNombre(request.getParameter("nombre"));
		autor.setPaisOrigen(request.getParameter("paisOrigen"));

		
		try{
			AutorDAO.update(autor);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}

		
	}
}
