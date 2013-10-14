package org.proyectoBiblioteca.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.dao.SocioDAO;
import org.proyectoBiblioteca.domain.Socio;
import org.proyectoBiblioteca.enums.EstadoSocio;

public class SocioService {

	public static void retrieveAll(HttpServletRequest request) {
		
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		List<Socio> socios = null;
		
		try{
			
			TypedQuery<Socio> query = em.createNamedQuery("Socio.findAllActive",Socio.class);
			
			if(!query.getResultList().isEmpty()){
	
				socios = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("socios", socios);
		
	}
/* Método no usado ya que no hay que eliminar los datos de la db
	public static void delete(HttpServletRequest request) {

		try{
			
			SocioDAO.delete(Long.parseLong(request.getParameter("id")));
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		//TODO revisar este método, ver si aviso o no cuando tengo éxito
	}
*/

	public static void delete(HttpServletRequest request) {

		Socio socio = null;
		
		try{
			socio = SocioDAO.find(Long.parseLong(request.getParameter("id")));
			
			socio.setEstado(EstadoSocio.inhabilitado);
			socio.setFechaBaja(new Date());
			
			SocioDAO.update(socio);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		//TODO revisar este método, ver si aviso o no cuando tengo éxito
	}
	
	public static void retrieveById(HttpServletRequest request){
		
		Socio socio = null;
		
		try{
			socio = SocioDAO.find(Long.parseLong(request.getParameter("id")));
			
			request.setAttribute("socio", socio);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}
	
	public static void retrieveStatesList(HttpServletRequest request){

		try{
			
			request.setAttribute("estados", EstadoSocio.values());
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}

	public static void saveSocio(HttpServletRequest request) {

		Socio socio = null;
		
		//Si es nuevo lo creo, sino lo obtengo
		
		if (request.getParameter("id") == null){
			
			socio = new Socio();
			socio.setFechaAlta(new Date());
		}else{
			
			socio = SocioDAO.find(Integer.parseInt(request.getParameter("id")));
		}
		
		//seteo atributos con los parámetros

		socio.setNombre(request.getParameter("nombre"));
		socio.setApellido(request.getParameter("apellido"));
		socio.setDni(Integer.parseInt(request.getParameter("dni")));
		socio.setEmail(request.getParameter("email"));
		socio.setTelefono(request.getParameter("telefono"));
		socio.setRango(Integer.parseInt(request.getParameter("rango")));
		socio.setEstado(EstadoSocio.valueOf(request.getParameter("estado")));
		//TODO agregar tema de dirección acá
		
		try{
			
			SocioDAO.update(socio);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}

		
	}
}
