package org.proyectoBiblioteca.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import org.proyectoBiblioteca.dao.EjemplarDAO;
import org.proyectoBiblioteca.dao.LibroDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Ejemplar;
import org.proyectoBiblioteca.domain.Libro;
import org.proyectoBiblioteca.enums.EstadoEjemplar;

public class EjemplarService {

	public static void retrieveAll(HttpServletRequest request) {
		
		//ejecuto una consulta para traer los ejemplares activas y los mando como atributo en la request
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		List<Ejemplar> ejemplares = null;
		
		try{
			
			TypedQuery<Ejemplar> query = em.createNamedQuery("Ejemplar.findAllActive",Ejemplar.class);
			
			if(!query.getResultList().isEmpty()){
	
				ejemplares = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("ejemplares", ejemplares);
		
	}

	public static void delete(HttpServletRequest request) {
		//hago la baja lógica del ejemplar
		Ejemplar ejemplar = null;
		
		try{
			ejemplar = EjemplarDAO.find(Long.parseLong(request.getParameter("id")));
			
			ejemplar.setEstado(EstadoEjemplar.inhabilitado);
			ejemplar.setFechaBaja(new Date());
			
			//TODO hacer el tema de motivo baja quizás en otra pantallita o popup
			
			EjemplarDAO.update(ejemplar);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//TODO revisar este método, ver si aviso o no cuando tengo éxito
	}
	
	public static void retrieveById(HttpServletRequest request){
		
		Ejemplar ejemplar = null;
		
		try{
			ejemplar = EjemplarDAO.find(Long.parseLong(request.getParameter("id")));
			
			request.setAttribute("ejemplar", ejemplar);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	public static void saveEjemplar(HttpServletRequest request) {

		Ejemplar ejemplar = null;
		
		//Si es nuevo lo creo, sino lo obtengo
		
		String parameterId = request.getParameter("id");
		
		if ("".equals(parameterId) || null == parameterId){
			
			ejemplar = new Ejemplar();
			ejemplar.setFechaAlta(new Date());

		}else{
			
			ejemplar = EjemplarDAO.find(Long.parseLong(parameterId));
		}
		
		//seteo atributos con los parámetros

		ejemplar.setLibro(LibroDAO.find(Long.parseLong(request.getParameter("libro"))));
		ejemplar.setAnio(Integer.parseInt(request.getParameter("anio")));
		ejemplar.setEstante(request.getParameter("estante"));
		ejemplar.setMueble(request.getParameter("mueble"));
		ejemplar.setPasillo(request.getParameter("pasillo"));
		ejemplar.setEstado(EstadoEjemplar.valueOf(request.getParameter("estado")));
		ejemplar.setNumeroEjemplar(Long.parseLong(request.getParameter("numeroEjemplar")));
		ejemplar.setPrecioDolares(Double.parseDouble(request.getParameter("precioDolares")));
		
		try{
			EjemplarDAO.update(ejemplar);
			
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}

		
	}

	public static void retrieveData(HttpServletRequest request) {
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		request.setAttribute("estados", EstadoEjemplar.values());
		
		try{

			TypedQuery<Libro> queryLibros = em.createNamedQuery("Libro.findAllActive",Libro.class);
			
			if(!queryLibros.getResultList().isEmpty()){
	
				request.setAttribute("libros",queryLibros.getResultList());
			}

		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
	}
}
