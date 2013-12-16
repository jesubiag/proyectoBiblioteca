package org.proyectoBiblioteca.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.proyectoBiblioteca.dao.EjemplarDAO;
import org.proyectoBiblioteca.dao.MovimientoDAO;
import org.proyectoBiblioteca.dao.PrestamoDAO;
import org.proyectoBiblioteca.dao.SocioDAO;
import org.proyectoBiblioteca.dao.UsuarioDAO;
import org.proyectoBiblioteca.domain.Ejemplar;
import org.proyectoBiblioteca.domain.Movimiento;
import org.proyectoBiblioteca.domain.Prestamo;
import org.proyectoBiblioteca.domain.Socio;
import org.proyectoBiblioteca.domain.Usuario;
import org.proyectoBiblioteca.enums.EstadoEjemplar;
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
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//paso los datos por la request
		request.setAttribute("ejemplar", ejemplar);
	}

	public static String save(HttpServletRequest request, HttpSession session) {
		
		String idEjemplar = request.getParameter("id");
		String idSocio = request.getParameter("idSocio");
		String prestamoLocal = request.getParameter("tipoPrestamo");
		String fecha = request.getParameter("fecha");
		Date fechaAcordada = Utilidades.getSimpleDate(fecha);
		
		Ejemplar ejemplar = null;
		Socio socio = null;
		socio = SocioDAO.find(Long.parseLong(idSocio));
		
		//Intento recuperar ejemplar
		try{
			ejemplar = EjemplarDAO.find(Long.parseLong(idEjemplar));
		}catch(Exception ex){
			//ex.printStackTrace(); //TODO ver si se saca o no (Ver en otros casos)
			session.setAttribute("mensajePrestamo","Se ha producido un error al intentar prestar el ejemplar indicado. Por favor intente nuevamente");
		}
		
		//seteo url para redirijir al final
		String ret = "Busqueda?type=detalleLibro&id=" + ejemplar.getLibro().getId();
		
		//Verifico el estado del ejemplar, si es disponible entonces lo presto, sino no.
		if(ejemplar.getEstado() != EstadoEjemplar.disponible){
			//En este caso el ejemplar no está disponible para prestar, creo mensaje y redirijo sin crear
			session.setAttribute("mensajePrestamo", "Préstamo no creado. El ejemplar no está disponible para ser prestado.");
		}else if(Utilidades.diasHabiles(fechaAcordada) > 10){//verifico días hábiles entre fecha actual y acordada no son mayores a 10
			//más de 10 días hábiles, error!
			session.setAttribute("mensajePrestamo","Préstamo no creado. La fecha de devolución ingresada incluye más de 10 días hábiles. Intente nuevamente con una fecha menor.");				
		
		}else if(socio == null){
			//no se encontró el socio
			session.setAttribute("mensajePrestamo", "Préstamo no creado. El nro. de socio ingresado no existe.");
	
		}else if(socio.getRango() < ejemplar.getLibro().getRango()){
			//socio de rango menor al necesario
			session.setAttribute("mensajePrestamo", "Préstamo no creado. El socio ingresado no posee el rango suficiente para recibir un ejemplar de este libro.");
		
		}else if(socio.getEstado() != EstadoSocio.habilitado){
			//socio no habilitado
			session.setAttribute("mensajePrestamo", "Préstamo no creado. El socio ingresado no se encuentra habilitado para recibir préstamos.");
		
		}else{
				
			Usuario usuario = null;
			
			try{
				//recupero usuario
				
				usuario = UsuarioDAO.find(Long.parseLong(request.getParameter("idSocio")));
				//seteo el estado del ejemplar a prestado
				ejemplar.setEstado(EstadoEjemplar.prestado);
				//creo el préstamo
				Prestamo prestamo = new Prestamo(socio,ejemplar,fechaAcordada,Boolean.parseBoolean(prestamoLocal));
				//persisto el préstamo creado
				prestamo = PrestamoDAO.create(prestamo);
				//actualizo ejemplar ya que se pudo crear el préstamo
				EjemplarDAO.update(ejemplar);
				//creo un movimiento representando la acción realizada
				Movimiento movimiento = new Movimiento(prestamo,usuario,TipoMovimiento.prestamo);
				MovimientoDAO.create(movimiento);
				
				
			}catch(Exception ex){
				//ex.printStackTrace();
				session.setAttribute("mensajePrestamo","Se ha producido un error al intentar crear el préstamo. Por favor intente nuevamente");
				return ret;
			}
	
			session.setAttribute("mensajePrestamo", "Préstamo creado con éxito.");
			
		}
		
		return ret;
	}

}
