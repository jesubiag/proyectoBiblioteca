package org.proyectoBiblioteca.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.Libro;
import org.proyectoBiblioteca.domain.Movimiento;
import org.proyectoBiblioteca.domain.Prestamo;
import org.proyectoBiblioteca.enums.EstadoPrestamo;
import org.proyectoBiblioteca.enums.TipoMovimiento;
import org.proyectoBiblioteca.utils.Utilidades;

public class ReporteService {

	public static void retirosMesAMes(HttpServletRequest request) {
		request.setAttribute("fecha", Utilidades.getSimpleDate(new Date()));
		
		Calendar hoy = Calendar.getInstance();
		Calendar auxCal = Calendar.getInstance();
		int i;
		int contador;
		Map<String, Integer> resultados = new LinkedHashMap<>();
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		List<Movimiento> movimientos = null;
		
		try{
			
			TypedQuery<Movimiento> query = em.createNamedQuery("Movimiento.findAll",Movimiento.class);
			
			if(!query.getResultList().isEmpty()){
	
				movimientos = query.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		
		for(i = 12; i >= 0; i--){//Para cada mes
			
			contador = 0;
			
			for(Movimiento mov : movimientos){//Para cada movimiento
				
				auxCal.setTime(mov.getFecha());
				
				if(( auxCal.get(Calendar.MONTH) == hoy.get(Calendar.MONTH) ) && ( mov.getTipoMovimiento() == TipoMovimiento.prestamo)){
					++contador;
				}
			}
			
			resultados.put(Utilidades.getMonthName(hoy.get(Calendar.MONTH)), contador);
			
			hoy.add(Calendar.MONTH, -1);
			
		}
		
		request.setAttribute("datos", resultados);
		
	}

	public static void devolucionesEnMora(HttpServletRequest request) {
		request.setAttribute("fecha", Utilidades.getSimpleDate(new Date()));
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		List<Prestamo> prestamos = null;
		
		try{
			
			TypedQuery<Prestamo> query = em.createNamedQuery("Prestamo.findByState",Prestamo.class);
			query.setParameter("estado", EstadoPrestamo.vencido);
			
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

	public static void librosMasRetirados(HttpServletRequest request) {
		
		request.setAttribute("fecha", Utilidades.getSimpleDate(new Date()));
		int contador;
		Map<Integer, Libro> resultados = new HashMap<>();
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		List<Libro> libros = null;
		List<Prestamo> prestamos = null;
		
		try{
			
			TypedQuery<Libro> queryLibros = em.createNamedQuery("Libro.findAll",Libro.class);
			TypedQuery<Prestamo> queryPrestamos = em.createNamedQuery("Prestamo.findAll",Prestamo.class);
			
			if(!queryLibros.getResultList().isEmpty()){
				libros = queryLibros.getResultList();
			}
			if(!queryPrestamos.getResultList().isEmpty()){
				prestamos = queryPrestamos.getResultList();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
		}
		
		
		//el siguiente ciclo me da vergüenza =o
		for(Libro libro : libros){//Para cada libro
			
			contador = 0;
			
			for(Prestamo prestamo : prestamos){//Para cada préstamo

				if(prestamo.getEjemplar().getLibro() == libro){
					++contador;
				}
			}
			
			resultados.put(contador, libro);
			
		}
		
		SortedMap<Integer,Libro> resultadosOrdenados = new TreeMap<>(Collections.reverseOrder());
		resultadosOrdenados.putAll(resultados);
		
		request.setAttribute("datos", resultadosOrdenados);
		
	}

	

}
