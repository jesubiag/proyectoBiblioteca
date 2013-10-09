import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.proyectoBiblioteca.dao.EjemplarDAO;
import org.proyectoBiblioteca.dao.LibroDAO;
import org.proyectoBiblioteca.dao.LocalidadDAO;
import org.proyectoBiblioteca.dao.PrestamoDAO;
import org.proyectoBiblioteca.dao.SocioDAO;
import org.proyectoBiblioteca.dao.UsuarioDAO;
import org.proyectoBiblioteca.domain.Autor;
import org.proyectoBiblioteca.domain.Direccion;
import org.proyectoBiblioteca.domain.Editorial;
import org.proyectoBiblioteca.domain.Ejemplar;
import org.proyectoBiblioteca.domain.Libro;
import org.proyectoBiblioteca.domain.Localidad;
import org.proyectoBiblioteca.domain.Prestamo;
import org.proyectoBiblioteca.domain.Provincia;
import org.proyectoBiblioteca.domain.Socio;
import org.proyectoBiblioteca.domain.Usuario;
import org.proyectoBiblioteca.enums.TipoUsuario;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		//Localidad localidad = new Localidad(new Provincia("Buenos Aires"),"Campana");
		
		Localidad localidad = LocalidadDAO.find(1);
		
		Direccion direccion = new Direccion("PeterSt","2804",666,localidad);
		
		Socio socio = new Socio(33333333, "Pedro", "Araujo", "peter@gmail.com","3489666666",direccion);
		
		SocioDAO.create(socio);
		
		/*
		
		socio.setNombre("Lucas J.");
		
		SocioDAO.update(socio);
		
		Usuario usuario = new Usuario("Juan","Perez","clave","administrador",TipoUsuario.administrador);
		
		UsuarioDAO.create(usuario);
		*/
		
		/*
		Autor autor = new Autor("Juan Pérez","Argentina");
		
		Editorial editorial = new Editorial("Editorial1","editorial1@bla.com");
		
		List<Autor> autores = new ArrayList<Autor>();
		
		autores.add(autor);
		
		Libro libro = new Libro(autores,editorial,"novela,drama","9783161484100","Argentina",5,"Libro 1");
		
		LibroDAO.create(libro);
		
		libro.setRango(4);
		
		LibroDAO.update(libro); 
		*/
		/*
		Libro libro1 = LibroDAO.find(1);
		
		Ejemplar ejemplar = new Ejemplar(libro1,2013,"estante 1","mueble 1","pasillo 1",1,200.5);
		
		EjemplarDAO.create(ejemplar);
		
		ejemplar.setAnio(2012);
		
		EjemplarDAO.update(ejemplar);
		*/
		
		/*
		Usuario usuario = UsuarioDAO.find("administrador");
		
		Socio socio = SocioDAO.find(1);
		
		Ejemplar ejemplar = EjemplarDAO.find(2);
		
		Date date = new Date();
		
		date.setDate(10); //cambiar por uno no deprecated
		
		Prestamo prestamo = new Prestamo(socio,ejemplar,usuario,date,false);
		
		PrestamoDAO.create(prestamo);
		*/
		
	}

}
