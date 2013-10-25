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
import org.proyectoBiblioteca.dao.SocioDAO;
import org.proyectoBiblioteca.domain.Direccion;
import org.proyectoBiblioteca.domain.Localidad;
import org.proyectoBiblioteca.domain.Provincia;
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
/* M�todo no usado ya que no hay que eliminar los datos de la db
	public static void delete(HttpServletRequest request) {

		try{
			
			SocioDAO.delete(Long.parseLong(request.getParameter("id")));
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		//TODO revisar este m�todo, ver si aviso o no cuando tengo �xito
	}
*/

	public static void delete(HttpServletRequest request) {

		Socio socio = null;
		
		try{
			socio = SocioDAO.find(Long.parseLong(request.getParameter("id")));
			
			socio.setEstado(EstadoSocio.inhabilitado);
			socio.setFechaBaja(new Date());
			
			SocioDAO.update(socio);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//TODO revisar este m�todo, ver si aviso o no cuando tengo �xito
	}
	
	public static void retrieveById(HttpServletRequest request){
		
		Socio socio = null;
		
		try{
			socio = SocioDAO.find(Long.parseLong(request.getParameter("id")));
			
			request.setAttribute("socio", socio);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public static void retrieveData(HttpServletRequest request){

		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		try{
			
			request.setAttribute("estados", EstadoSocio.values());
			
			TypedQuery<Provincia> queryProvincias = em.createNamedQuery("Provincia.findAll",Provincia.class);
			
			if(!queryProvincias.getResultList().isEmpty()){
	
				request.setAttribute("provincias",queryProvincias.getResultList());
			}
			
			TypedQuery<Localidad> queryLocalidades = em.createNamedQuery("Localidad.findAllActive",Localidad.class);
			
			if(!queryLocalidades.getResultList().isEmpty()){
				
				request.setAttribute("localidades", queryLocalidades.getResultList());
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	public static void saveSocio(HttpServletRequest request) {

		Socio socio = null;
		Localidad localidad = null;
		
		//Si es nuevo lo creo, sino lo obtengo
		
		String parameterId = request.getParameter("id");
		
		if ("".equals(parameterId) || null == parameterId){
			
			socio = new Socio();
			Direccion direccion = new Direccion();
			socio.setDireccion(direccion);
			socio.setFechaAlta(new Date());
		}else{
			
			socio = SocioDAO.find(Long.parseLong(parameterId));
		}
		
		//seteo atributos del socio con los par�metros de la request

		socio.setNombre(request.getParameter("nombre"));
		socio.setApellido(request.getParameter("apellido"));
		socio.setDni(Integer.parseInt(request.getParameter("dni")));
		socio.setEmail(request.getParameter("email"));
		socio.setTelefono(request.getParameter("telefono"));
		socio.setRango(Integer.parseInt(request.getParameter("rango")));
		socio.setEstado(EstadoSocio.valueOf(request.getParameter("estado")));
		
		socio.getDireccion().setCalle(request.getParameter("calle"));
		socio.getDireccion().setNumero(Integer.parseInt(request.getParameter("numero")));
		socio.getDireccion().setCodigoPostal(request.getParameter("codigoPostal"));
				
		String parameterPiso = request.getParameter("piso");
		
		String parameterDepartamento = request.getParameter("departamento");
		 
		if(!"".equals(parameterPiso)){
			socio.getDireccion().setPiso(Integer.parseInt(parameterPiso));
		}
		
		if(!"".equals(parameterDepartamento)){
			socio.getDireccion().setDepartamento(parameterDepartamento);
		}
		
		localidad = LocalidadDAO.findByFields(request.getParameter("provincia"),request.getParameter("localidad"));
		
		if (localidad == null){ //Si es nula es porque no existe, la creo
			localidad = new Localidad(ProvinciaDAO.find(request.getParameter("provincia")),request.getParameter("localidad"));
		}
		
		socio.getDireccion().setLocalidad(localidad);

		
		try{
			SocioDAO.update(socio);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}

		
	}
}
