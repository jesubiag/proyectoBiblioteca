package org.proyectoBiblioteca.test.java;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.proyectoBiblioteca.dao.AutorDAO;
import org.proyectoBiblioteca.domain.Autor;

public class AutorDAOTest {
	
	private static Autor autor1;
	private static long id1;
	
	@BeforeClass
	public static void setUp() throws Exception {
		autor1 = new Autor("Autor de Prueba","Argentina");
		AutorDAO.create(autor1);
		id1 = autor1.getId();
	}
	
	@Test
	public void testFind() {
		assertEquals(autor1.getId(),AutorDAO.find(id1).getId());
	}
	
	@Test
	public void testUpdate() {
		autor1.setNombre("Autor Modificado");
		AutorDAO.update(autor1);
		assertEquals("Autor Modificado",AutorDAO.find(id1).getNombre());
	}
	
	@Ignore //Probado en otro método
	public void testCreate(){
		fail("Not yet implemented");
	}
	
	@Ignore //No se usa (baja lógica)
	public void testDelete(){
		fail("Not yet implemented");
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		AutorDAO.delete(id1);
	}
	
}
