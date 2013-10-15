package org.proyectoBiblioteca.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utilidades {

	public static String getSimpleDate(Date fecha){
			
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String ret = null;
		 
		ret = dateFormat.format(fecha);
		
		return ret;
			
		}

	public static Date getSimpleDate(String fecha){
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date ret = null;
		
		try {
			
			ret = dateFormat.parse(fecha);
		
		} catch (ParseException ex) {
		    ex.printStackTrace();
		}
		
		return ret;
		
		}
	
}
