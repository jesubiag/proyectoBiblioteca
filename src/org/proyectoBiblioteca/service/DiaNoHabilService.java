package org.proyectoBiblioteca.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.proyectoBiblioteca.dao.DiaNoHabilDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.DiaNoHabil;
import org.proyectoBiblioteca.utils.Utilidades;

public class DiaNoHabilService {

	public static void retrieveAll(HttpServletRequest request) {
		
		//ejecuto una consulta para traer los diaNoHabiles activos y los mando como atributo en la request
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		List<DiaNoHabil> diasNoHabiles = null;
		
		try{
			
			TypedQuery<DiaNoHabil> query = em.createNamedQuery("DiaNoHabil.findAllActive",DiaNoHabil.class);
			
			if(!query.getResultList().isEmpty()){
	
				diasNoHabiles = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("diasNoHabiles", diasNoHabiles);
		
	}

	public static void delete(HttpServletRequest request) {
		//hago la baja lógica del diaNoHabil
		DiaNoHabil diaNoHabil = null;
		HttpSession session = request.getSession();
		
		try{
			diaNoHabil = DiaNoHabilDAO.find(Long.parseLong(request.getParameter("id")));
			
			diaNoHabil.setActivo(false);
			
			DiaNoHabilDAO.update(diaNoHabil);
			
			session.setAttribute("mensajeAccion", "Baja de Día No Hábil realizada con éxito.");
			
		}catch(NumberFormatException ex){
			session.setAttribute("mensajeAccion", "Baja de Día No Hábil no realizada. Intente nuevamente.");
		}
		
	}
	
	public static void retrieveById(HttpServletRequest request){
		
		DiaNoHabil diaNoHabil = null;
		
		try{
			diaNoHabil = DiaNoHabilDAO.find(Long.parseLong(request.getParameter("id")));
			
			request.setAttribute("diaNoHabil", diaNoHabil);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}

	public static void saveDiaNoHabil(HttpServletRequest request) {

		HttpSession session = request.getSession();
		DiaNoHabil diaNoHabil = null;
		
		//Si es nuevo lo creo, sino lo obtengo
		
		String parameterId = request.getParameter("id");
		
		if ("".equals(parameterId) || null == parameterId){
			
			diaNoHabil = new DiaNoHabil();

		}else{
			
			diaNoHabil = DiaNoHabilDAO.find(Long.parseLong(parameterId));
		}
		
		//seteo atributos con los parámetros

		diaNoHabil.setMotivo(request.getParameter("motivo"));

		//Paso las strings parámetro que son fechas a un objeto Date
		
		diaNoHabil.setFecha(Utilidades.getSimpleDate(request.getParameter("fecha")));
		diaNoHabil.setFechaSiguiente(Utilidades.getSimpleDate(request.getParameter("fechaSiguiente")));

		//Intento persistir cambios o nuevo objeto
		 
		try{
			DiaNoHabilDAO.update(diaNoHabil);
			session.setAttribute("mensajeAccion", "Alta/Mod. de Día No Hábil realizada con éxito.");
			
		}catch(NumberFormatException ex){
			session.setAttribute("mensajeAccion", "Alta/Mod. de Día No Hábil no realizada. Intente nuevamente.");
		}

		
	}
}
