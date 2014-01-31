package org.proyectoBiblioteca.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.proyectoBiblioteca.dao.LocalidadDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.dao.ProvinciaDAO;
import org.proyectoBiblioteca.domain.Localidad;
import org.proyectoBiblioteca.domain.Provincia;

public class LocalidadService {

	public static void retrieveAll(HttpServletRequest request) {
		
		//ejecuto una consulta para traer los localidades activas y los mando como atributo en la request
		
		EntityManager em = PersistenceManager.getEntityManager();
		
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
		HttpSession session = request.getSession();
		Localidad localidad = null;
		
		try{
			localidad = LocalidadDAO.find(Long.parseLong(request.getParameter("id")));
			
			localidad.setActivo(false);
			localidad.setFechaBaja(new Date());
			
			LocalidadDAO.update(localidad);
			session.setAttribute("mensajeAccion", "Baja de localidad realizada con éxito.");
			
		}catch(NumberFormatException ex){
			session.setAttribute("mensajeAccion", "Baja de localidad no realizada. Intente nuevamente.");
		}
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

		HttpSession session = request.getSession();
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
			session.setAttribute("mensajeAccion", "Alta/Mod. de editorial realizada con éxito.");
			
		}catch(NumberFormatException ex){
			session.setAttribute("mensajeAccion", "Alta/Mod. de editorial no realizada. Intente nuevamente.");
		}

		
	}

	public static void retrieveData(HttpServletRequest request) {
		
		EntityManager em = PersistenceManager.getEntityManager();
		
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
