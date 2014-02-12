package org.proyectoBiblioteca.test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.proyectoBiblioteca.dao.AutorDAO;
import org.proyectoBiblioteca.dao.EditorialDAO;
import org.proyectoBiblioteca.dao.EjemplarDAO;
import org.proyectoBiblioteca.dao.LibroDAO;
import org.proyectoBiblioteca.dao.LocalidadDAO;
import org.proyectoBiblioteca.dao.PrestamoDAO;
import org.proyectoBiblioteca.dao.ProvinciaDAO;
import org.proyectoBiblioteca.dao.SocioDAO;
import org.proyectoBiblioteca.dao.SuspensionDAO;
import org.proyectoBiblioteca.domain.Autor;
import org.proyectoBiblioteca.domain.Direccion;
import org.proyectoBiblioteca.domain.Editorial;
import org.proyectoBiblioteca.domain.Ejemplar;
import org.proyectoBiblioteca.domain.Libro;
import org.proyectoBiblioteca.domain.Localidad;
import org.proyectoBiblioteca.domain.Prestamo;
import org.proyectoBiblioteca.domain.Socio;
import org.proyectoBiblioteca.domain.Suspension;

public class SuspensionDAOTest {
	
	private static Suspension suspension;
	private static long id;
	private static long idPrestamo;
	private static long idSocio;
	private static long idLocalidad;
	private static long idEjemplar;
	private static long idLibro;
	private static long idAutor;
	private static long idEditorial;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Creo localidad
		Localidad localidad = new Localidad(ProvinciaDAO.find("Buenos Aires"),"Localidad inexistente");
		idLocalidad = localidad.getId();
		//Creo direccion
		Direccion direccion = new Direccion("Calle Falsa","2804",125,localidad);
		Socio socio = new Socio(12345677,"The","Doctor","doctor@who.com","445789",direccion);
		SocioDAO.create(socio);
		idSocio = socio.getId();
		idLocalidad = localidad.getId();
		//creo editorial
		ArrayList<String> telefonos = new ArrayList<String>(Arrays.asList(new String[]{"444323","456223574"}));
		Editorial editorial = new Editorial();
		editorial.setTelefonos(telefonos);
		editorial.setFechaAlta(new Date());
		editorial.setNombre("Editorial de prueba");
		editorial.setEmail("prueba@gmail.com");
		EditorialDAO.create(editorial);
		idEditorial = editorial.getId();
		//creo autores
		Autor autor = new Autor("Pedro Prueba","Argentina");
		AutorDAO.create(autor);
		idAutor = autor.getId();
		ArrayList<Autor> autores = new ArrayList<Autor>();
		autores.add(autor);
		//creo etiquetas
		ArrayList<String> etiquetas = new ArrayList<String>(Arrays.asList(new String[]{"etiqueta1", "etiqueta2"}));
		//creo el libro
		Libro libro = new Libro(autores,editorial,etiquetas,"1234567894125","Argentina",5,"Libro de pruebas","descripción de prueba","linkImagen");
		LibroDAO.create(libro);
		idLibro = libro.getId();
		//creo el ejemplar
		Ejemplar ejemplar = new Ejemplar(libro,2014,"12","32","12",99999,250);
		EjemplarDAO.create(ejemplar);
		idEjemplar = ejemplar.getId();
		Prestamo prestamo = new Prestamo(socio,ejemplar,new Date(),false);
		PrestamoDAO.create(prestamo);
		idPrestamo = prestamo.getId();
		
		suspension = new Suspension(prestamo,socio,"motivo de susp.",3);
		SuspensionDAO.create(suspension);
		id = suspension.getId();
	}

	@Test
	public void testFind() {
		assertEquals(suspension.getId(),SuspensionDAO.find(id).getId());
	}

	@Test
	public void testUpdate() {
		suspension.setMotivo("Motivo modificado");
		SuspensionDAO.update(suspension);
		assertEquals("Motivo modificado",SuspensionDAO.find(id).getMotivo());
	}

	@Ignore //Probado en otro método
	public void testCreate() {
		fail("Not yet implemented");
	}
	
	@Ignore //No se usa
	public void testDelete() {
		fail("Not yet implemented");
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		SuspensionDAO.delete(id);
		PrestamoDAO.delete(idPrestamo);
		EjemplarDAO.delete(idEjemplar);
		LibroDAO.delete(idLibro);
		EditorialDAO.delete(idEditorial);
		AutorDAO.delete(idAutor);
		SocioDAO.delete(idSocio);
		LocalidadDAO.delete(idLocalidad);
	}

}
