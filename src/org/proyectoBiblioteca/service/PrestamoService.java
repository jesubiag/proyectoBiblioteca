package org.proyectoBiblioteca.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.proyectoBiblioteca.dao.EjemplarDAO;
import org.proyectoBiblioteca.dao.MovimientoDAO;
import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.dao.PrestamoDAO;
import org.proyectoBiblioteca.dao.SocioDAO;
import org.proyectoBiblioteca.dao.UsuarioDAO;
import org.proyectoBiblioteca.domain.Ejemplar;
import org.proyectoBiblioteca.domain.Movimiento;
import org.proyectoBiblioteca.domain.Prestamo;
import org.proyectoBiblioteca.domain.Socio;
import org.proyectoBiblioteca.domain.Usuario;
import org.proyectoBiblioteca.enums.EstadoEjemplar;
import org.proyectoBiblioteca.enums.EstadoPrestamo;
import org.proyectoBiblioteca.enums.EstadoSocio;
import org.proyectoBiblioteca.enums.TipoMovimiento;
import org.proyectoBiblioteca.utils.Utilidades;

public class PrestamoService {

	public static void retrieveData(HttpServletRequest request) {

		//obtengo el id del ejemplar a prestar
		String id = request.getParameter("id");
		
		//traigo el ejemplar
		Ejemplar ejemplar = null;

		try{
			ejemplar = EjemplarDAO.find(Long.parseLong(id));
			
			if(ejemplar.getEstado() == EstadoEjemplar.prestado){
				//esto es probablemente una devoluci�n: busco datos de pr�stamo
				Prestamo prestamo = PrestamoService.getLoanForCopyId(ejemplar.getId());

				request.setAttribute("prestamo", prestamo);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//paso los datos por la request
		request.setAttribute("ejemplar", ejemplar);
	}

	public static Prestamo getLoanForCopyId(long id) {
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		Prestamo prestamo = null;
		
		try{
			TypedQuery<Prestamo> query = em.createNamedQuery("Prestamo.findActiveByCopyId",Prestamo.class);
			query.setParameter("idEjemplar", id);
			
			if(!query.getResultList().isEmpty()){
				prestamo = query.getResultList().get(0);
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		return prestamo;
	}

	public static String saveLoan(HttpServletRequest request, HttpSession session) {
		
		String idEjemplar = request.getParameter("id");
		String idSocio = request.getParameter("idSocio");
		String prestamoLocal = request.getParameter("tipoPrestamo");
		String fecha = request.getParameter("fecha");
		Date fechaAcordada = Utilidades.getSimpleDate(fecha);
		
		Ejemplar ejemplar = null;
		Socio socio = null;

		//Intento recuperar ejemplar y socio
		try{
			socio = SocioDAO.find(Long.parseLong(idSocio));
			ejemplar = EjemplarDAO.find(Long.parseLong(idEjemplar));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//seteo url para redirijir al final
		String ret = "Busqueda?type=detalleLibro&id=" + ejemplar.getLibro().getId();
		
		//Verifico el estado del ejemplar, si es disponible entonces lo presto, sino no.
		if(ejemplar.getEstado() != EstadoEjemplar.disponible){
			//En este caso el ejemplar no est� disponible para prestar, creo mensaje y redirijo sin crear
			session.setAttribute("mensajePrestamo", "Pr�stamo no creado. El ejemplar no est� disponible para ser prestado.");
		}else if(Utilidades.diasHabiles(new Date(),fechaAcordada) > 10){//verifico d�as h�biles entre fecha actual y acordada no son mayores a 10
			//m�s de 10 d�as h�biles, error!
			session.setAttribute("mensajePrestamo","Pr�stamo no creado. La fecha de devoluci�n ingresada incluye m�s de 10 d�as h�biles. Intente nuevamente con una fecha menor.");				
		
		}else if(socio == null){
			//no se encontr� el socio
			session.setAttribute("mensajePrestamo", "Pr�stamo no creado. El nro. de socio ingresado no existe.");
	
		}else if(socio.getRango() < ejemplar.getLibro().getRango()){
			//socio de rango menor al necesario
			session.setAttribute("mensajePrestamo", "Pr�stamo no creado. El socio ingresado no posee el rango suficiente para recibir un ejemplar de este libro.");
		
		}else if(socio.getEstado() != EstadoSocio.habilitado){
			//socio no habilitado
			session.setAttribute("mensajePrestamo", "Pr�stamo no creado. El socio ingresado no se encuentra habilitado para recibir pr�stamos.");
		
		}else{//por �ltimo chequeo si el usuario tiene o no menos de 2 pr�stamos activos
			
			EntityManager em = PersistenceManager.getEntityManager();
			
			List<Prestamo> prestamos = null;
			
			try{
				TypedQuery<Prestamo> query = em.createNamedQuery("Prestamo.findActiveByMemberId",Prestamo.class);
				query.setParameter("idSocio", socio.getId());
				
				if(!query.getResultList().isEmpty()){
					prestamos = query.getResultList();
				}
			
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				em.close();
			}
			
			if(prestamos != null && prestamos.size() >= 2){

				//socio con 2 pr�stamos activos
				session.setAttribute("mensajePrestamo", "Pr�stamo no creado. El socio ingresado ya posee demasiados pr�stamos activos.");
				
			}else{
			
				Usuario usuario = null;
				
				try{
					//recupero usuario
					
					usuario = UsuarioDAO.find(Long.parseLong(request.getParameter("idUsuario")));
					//seteo el estado del ejemplar a prestado
					ejemplar.setEstado(EstadoEjemplar.prestado);
					//creo el pr�stamo
					Prestamo prestamo = new Prestamo(socio,ejemplar,fechaAcordada,Boolean.parseBoolean(prestamoLocal));
					//persisto el pr�stamo creado
					prestamo = PrestamoDAO.create(prestamo);
					//actualizo ejemplar ya que se pudo crear el pr�stamo
					EjemplarDAO.update(ejemplar);
					//creo un movimiento representando la acci�n realizada
					Movimiento movimiento = new Movimiento(prestamo,usuario,TipoMovimiento.prestamo);
					MovimientoDAO.create(movimiento);
					
					
				}catch(Exception ex){
					//ex.printStackTrace();
					session.setAttribute("mensajePrestamo","Se ha producido un error al intentar crear el pr�stamo. Por favor intente nuevamente");
					return ret;
				}
		
				session.setAttribute("mensajePrestamo", "Pr�stamo creado con �xito.");
				
			}
			
		}
		return ret;
		
	}

	public static String saveReturn(HttpServletRequest request,
			HttpSession session) {
			
		String idEjemplar = request.getParameter("idEjemplar");
		String idPrestamo = request.getParameter("idPrestamo");
		String idUsuario = request.getParameter("idUsuario");
		
		Ejemplar ejemplar = null;
		Prestamo prestamo = null;
		Usuario usuario = null;
		
		String mensajeSuspension = "";
		
		try{//Recupero datos de la db
			prestamo = PrestamoDAO.find(Long.parseLong(idPrestamo));
			ejemplar = EjemplarDAO.find(Long.parseLong(idEjemplar));
			usuario = UsuarioDAO.find(Long.parseLong(idUsuario));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//seteo url para redirijir al final
		String ret = "Busqueda?type=detalleLibro&id=" + ejemplar.getLibro().getId();
		
		//Veo tema de suspensi�n del socio
		if(prestamo.getEstado() == EstadoPrestamo.vencido){
			//calculo cu�ntos d�as h�biles pasaron desde vencimiento
			int diasVencidos = Utilidades.diasHabiles(prestamo.getFechaAcordada(), new Date());
			SocioService.suspend(diasVencidos,prestamo);	
			mensajeSuspension = "El socio nro: " + prestamo.getSocio().getId() + " ha sido suspendido por " + diasVencidos*2 + " d�a/s.";
		}
		
		try{//Actualizo los datos del pr�stamo y lo intento registrar
			prestamo.setEstado(EstadoPrestamo.devuelto);
			prestamo.setFechaDevolucion(new Date());
			prestamo.setNota(request.getParameter("nota"));
			PrestamoDAO.update(prestamo);
			//Actualizo estado de ejemplar
			ejemplar.setEstado(EstadoEjemplar.disponible);
			EjemplarDAO.update(ejemplar);
			session.setAttribute("mensajePrestamo", "Ejemplar devuelto correctamente. " + mensajeSuspension);
		
			//genero un movimiento indicando la devoluci�n
			Movimiento movimiento = new Movimiento(prestamo,usuario,TipoMovimiento.devolucion);
			MovimientoDAO.create(movimiento);
			
		}catch(Exception ex){
			session.setAttribute("mensajePrestamo", "Devoluci�n no realizada. Error al intentar registrar la devoluci�n. Intente nuevamente.");
			
			return ret;
		}
		
		
		return ret;
	}

	public static void retrieveLoans(HttpServletRequest request) {

		EntityManager em = PersistenceManager.getEntityManager();
		
		List<Prestamo> prestamos = null;
		
		try{
			TypedQuery<Prestamo> query = em.createNamedQuery("Prestamo.findAllActive",Prestamo.class);
			
			if(!query.getResultList().isEmpty()){
				prestamos = query.getResultList();
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("prestamos", prestamos);
		
	}

	public static void retrieveLoansForMemberId(long idSocio, HttpServletRequest request) {

		EntityManager em = PersistenceManager.getEntityManager();
		
		List<Prestamo> prestamos = null;
		
		try{
			TypedQuery<Prestamo> query = em.createNamedQuery("Prestamo.findActiveByMemberId",Prestamo.class);
			query.setParameter("idSocio", idSocio);
			if(!query.getResultList().isEmpty()){
				prestamos = query.getResultList();
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		request.setAttribute("prestamos", prestamos);
		
	}
}
