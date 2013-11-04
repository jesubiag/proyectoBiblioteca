package org.proyectoBiblioteca.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.proyectoBiblioteca.dao.EditorialDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Editorial;

public class EditorialService {

	public static void retrieveAll(HttpServletRequest request) {
		
		//ejecuto una consulta para traer los editoriales activos y los mando como atributo en la request
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
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
		
		try{
			editorial = EditorialDAO.find(Long.parseLong(request.getParameter("id")));
			
			editorial.setActivo(false);
			editorial.setFechaBaja(new Date());
			
			EditorialDAO.update(editorial);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		//TODO revisar este método, ver si aviso o no cuando tengo éxito
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

		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}

		
	}
}
