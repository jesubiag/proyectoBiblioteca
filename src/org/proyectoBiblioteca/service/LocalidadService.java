package org.proyectoBiblioteca.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.proyectoBiblioteca.dao.LocalidadDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.dao.ProvinciaDAO;
import org.proyectoBiblioteca.domain.Localidad;
import org.proyectoBiblioteca.domain.Provincia;

public class LocalidadService {

	public static void retrieveAll(HttpServletRequest request) {
		
		//ejecuto una consulta para traer los localidades activas y los mando como atributo en la request
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		List<Localidad> localidades = null;
		
		try{
			
			TypedQuery<Localidad> query = em.createNamedQuery("Localidad.findAllActive",Localidad.class);
			
			if(!query.getResultList().isEmpty()){
	
				localidades = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("localidades", localidades);
		
	}

	public static void delete(HttpServletRequest request) {
		//hago la baja lógica del localidad
		Localidad localidad = null;
		
		try{
			localidad = LocalidadDAO.find(Long.parseLong(request.getParameter("id")));
			
			localidad.setActivo(false);
			localidad.setFechaBaja(new Date());
			
			LocalidadDAO.update(localidad);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		//TODO revisar este método, ver si aviso o no cuando tengo éxito
	}
	
	public static void retrieveById(HttpServletRequest request){
		
		Localidad localidad = null;
		
		try{
			localidad = LocalidadDAO.find(Long.parseLong(request.getParameter("id")));
			
			request.setAttribute("localidad", localidad);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}

	public static void saveLocalidad(HttpServletRequest request) {

		Localidad localidad = null;
		
		//Si es nuevo lo creo, sino lo obtengo
		
		String parameterId = request.getParameter("id");
		
		if ("".equals(parameterId) || null == parameterId){
			
			localidad = new Localidad();
			localidad.setFechaAlta(new Date());

		}else{
			
			localidad = LocalidadDAO.find(Long.parseLong(parameterId));
		}
		
		//seteo atributos con los parámetros

		localidad.setNombre(request.getParameter("nombre"));
		localidad.setProvincia(ProvinciaDAO.find(request.getParameter("provincia")));

		
		try{
			LocalidadDAO.update(localidad);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}

		
	}

	public static void retrieveData(HttpServletRequest request) {
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		try{

			TypedQuery<Provincia> queryProvincias = em.createNamedQuery("Provincia.findAll",Provincia.class);
			
			if(!queryProvincias.getResultList().isEmpty()){
	
				request.setAttribute("provincias",queryProvincias.getResultList());
			}

		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}
}
