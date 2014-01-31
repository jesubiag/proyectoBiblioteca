package org.proyectoBiblioteca.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.proyectoBiblioteca.dao.EditorialDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Editorial;

public class EditorialService {

	public static void retrieveAll(HttpServletRequest request) {
		
		//ejecuto una consulta para traer los editoriales activos y los mando como atributo en la request
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		List<Editorial> editoriales = null;
		
		try{
			
			TypedQuery<Editorial> query = em.createNamedQuery("Editorial.findAllActive",Editorial.class);
			
			if(!query.getResultList().isEmpty()){
	
				editoriales = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("editoriales", editoriales);
		
	}

	public static void delete(HttpServletRequest request) {
		//hago la baja lógica del editorial
		Editorial editorial = null;
		HttpSession session = request.getSession();
		
		try{
			editorial = EditorialDAO.find(Long.parseLong(request.getParameter("id")));
			
			editorial.setActivo(false);
			editorial.setFechaBaja(new Date());
			
			EditorialDAO.update(editorial);
			session.setAttribute("mensajeAccion", "Baja de editorial realizada con éxito.");
			
		}catch(NumberFormatException ex){
			session.setAttribute("mensajeAccion", "Baja de editorial no realizada. Intente nuevamente.");
		}
		
	}
	
	public static void retrieveById(HttpServletRequest request){
		
		Editorial editorial = null;
		
		try{
			editorial = EditorialDAO.find(Long.parseLong(request.getParameter("id")));
			
			request.setAttribute("editorial", editorial);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}

	public static void saveEditorial(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Editorial editorial = null;
		
		//Si es nuevo lo creo, sino lo obtengo
		
		String parameterId = request.getParameter("id");
		
		if ("".equals(parameterId) || null == parameterId){
			
			editorial = new Editorial();
			editorial.setFechaAlta(new Date());

		}else{
			
			editorial = EditorialDAO.find(Long.parseLong(parameterId));
		}
		
		//seteo atributos con los parámetros

		editorial.setNombre(request.getParameter("nombre"));
		editorial.setEmail(request.getParameter("email"));
		
		String[] telefonos = request.getParameterValues("telefonos[]");
		
		editorial.setTelefonos(new ArrayList<String>(Arrays.asList(telefonos)));
		
		//TODO implementar direcciones y telefonos
		
		
		try{
			EditorialDAO.update(editorial);
			
			session.setAttribute("mensajeAccion", "Alta/Mod. de editorial realizada con éxito.");

		}catch(NumberFormatException ex){
			session.setAttribute("mensajeAccion", "Alta/Mod. de editorial no realizada. Intente nuevamente.");
		}

		
	}
}
