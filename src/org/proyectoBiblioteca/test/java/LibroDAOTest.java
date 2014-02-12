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
import org.proyectoBiblioteca.dao.LibroDAO;
import org.proyectoBiblioteca.domain.Autor;
import org.proyectoBiblioteca.domain.Editorial;
import org.proyectoBiblioteca.domain.Libro;

public class LibroDAOTest {

	private static Libro libro;
	private static long id;
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
		libro = new Libro(autores,editorial,etiquetas,"1234567894125","Argentina",5,"Libro de pruebas","descripción de prueba","linkImagen");
		LibroDAO.create(libro);
		id = libro.getId();
	}

	@Test
	public void testFind() {
		assertEquals(libro.getId(),LibroDAO.find(id).getId());
	}

	@Test
	public void testUpdate() {
		libro.setTitulo("Libro modificado");
		LibroDAO.update(libro);
		assertEquals("Libro modificado",LibroDAO.find(id).getTitulo());
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
		LibroDAO.delete(id);
		EditorialDAO.delete(idEditorial);
		AutorDAO.delete(idAutor);
	}

}
