package org.proyectoBiblioteca.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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
	
}
