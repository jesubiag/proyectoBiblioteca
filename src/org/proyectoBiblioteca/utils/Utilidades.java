package org.proyectoBiblioteca.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.proyectoBiblioteca.dao.PersistenceManager;
import org.proyectoBiblioteca.domain.DiaNoHabil;

public class Utilidades {

	public static String getSimpleDate(Date fecha){
			
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",new Locale("es","AR"));
		String ret = null;
		 
		ret = dateFormat.format(fecha);
		
		return ret;
			
		}

	public static Date getSimpleDate(String fecha){
	
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",new Locale("es","AR"));
		Date ret = null;
		
		try {
			
			ret = dateFormat.parse(fecha);
		
		} catch (ParseException ex) {
		    ex.printStackTrace();
		}
		
		return ret;
		
		}

	public static String stringListAsString(List<String> lista) {
		
		String ret = "";
		
		if (lista.size() > 1){
			
			for(String elemento : lista){
			
				if("".equals(ret)){
					ret = elemento;
				}else{
					ret = elemento + ", " + ret;
				}
			}			
			
		}else if(lista.size() == 1){
			ret = lista.get(0);
		}
		
		return ret;
	}
	
	public static <T> String listAsString(List<T> lista) {
		
		String ret = "";
		
		if (lista.size() > 1){
			
			for(T elemento : lista){
			
				if("".equals(ret)){
					ret = elemento.toString();
				}else{
					ret = elemento.toString() + ", " + ret;
				}
			}			
			
		}else if(lista.size() == 1){
			ret = lista.get(0).toString();
		}
		
		return ret;
	}
	
	public static int diasHabiles(Date fechaDesde, Date fechaHasta){
		
		int ret = 0;
		
		Calendar startCal = new GregorianCalendar();
		startCal.setTime(fechaDesde);
		startCal.set(Calendar.HOUR_OF_DAY,0);
		startCal.set(Calendar.MINUTE,0);
		startCal.set(Calendar.SECOND,0);
		startCal.set(Calendar.MILLISECOND,0);
		
		Calendar endCal = new GregorianCalendar();
		endCal.setTime(fechaHasta);
		endCal.set(Calendar.HOUR_OF_DAY,0);
		endCal.set(Calendar.MINUTE,0);
		endCal.set(Calendar.SECOND,0);
		endCal.set(Calendar.MILLISECOND,0);
		
		Calendar auxCal = Calendar.getInstance();
		
		//obtengo días no hábiles
		
		EntityManager em = PersistenceManager.getEntityManager();
		
		List<DiaNoHabil> resultado = null;
		List<Integer> diasNoHabiles = new ArrayList<>();
		
		try{
			
			TypedQuery<DiaNoHabil> query = em.createNamedQuery("DiaNoHabil.findFollowing",DiaNoHabil.class);
			
			if(!query.getResultList().isEmpty()){
	
				resultado = query.getResultList();
			}
			

		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			em.close();
			//lleno el array con las fechas no hábiles en formato Calendar
			for(DiaNoHabil d : resultado){
				
				auxCal.setTime(d.getFecha());
				/*auxCal.set(Calendar.HOUR_OF_DAY,0);
				auxCal.set(Calendar.MINUTE,0);
				auxCal.set(Calendar.SECOND,0);
				auxCal.set(Calendar.MILLISECOND,0);*/
				diasNoHabiles.add(auxCal.get(Calendar.DAY_OF_YEAR));
			}
		}
		
		do {
	          startCal.add(Calendar.DAY_OF_MONTH, 1);
	          if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
	          && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
	          && !diasNoHabiles.contains((Integer)startCal.get(Calendar.DAY_OF_YEAR)))
	          {
	        	  ++ret;//es hábil, sumo 1 al contador
	          }
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis());
		
		return ret;
	}
	
	public static String getMonthName(int month){//para obtener el nombre del mes (empieza desde 0 para usar con Calendar)
	    String[] monthNames = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	    return monthNames[month];
	}
	
}
