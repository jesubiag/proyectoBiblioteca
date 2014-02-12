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
import org.proyectoBiblioteca.domain.Autor;
import org.proyectoBiblioteca.domain.Editorial;
import org.proyectoBiblioteca.domain.Ejemplar;
import org.proyectoBiblioteca.domain.Libro;
import org.proyectoBiblioteca.enums.EstadoEjemplar;

public class EjemplarDAOTest {

	private static Ejemplar ejemplar;
	private static long id;
	private static long idLibro;
	private static long idEditorial;
	private static long idAutor;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		ejemplar = new Ejemplar(libro,2014,"12","32","12",99999,250);
		EjemplarDAO.create(ejemplar);
		id = ejemplar.getId();
	}

	@Test
	public void testFind() {
		assertEquals(ejemplar.getId(),EjemplarDAO.find(id).getId());
	}

	@Test
	public void testUpdate() {
		ejemplar.setEstado(EstadoEjemplar.inhabilitado);
		EjemplarDAO.update(ejemplar);
		assertEquals(EstadoEjemplar.inhabilitado,EjemplarDAO.find(id).getEstado());
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
		EjemplarDAO.delete(id);
		LibroDAO.delete(idLibro);
		EditorialDAO.delete(idEditorial);
		AutorDAO.delete(idAutor);
	}

}
