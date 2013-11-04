package org.proyectoBiblioteca.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
	
	public static <T> String ListAsString(List<T> lista) {
		
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
	
}
