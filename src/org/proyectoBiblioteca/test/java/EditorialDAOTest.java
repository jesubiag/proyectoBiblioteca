package org.proyectoBiblioteca.test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.proyectoBiblioteca.dao.EditorialDAO;
import org.proyectoBiblioteca.domain.Editorial;

public class EditorialDAOTest {

	private static Editorial editorial;
	private static long id;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		ArrayList<String> telefonos = new ArrayList<String>(Arrays.asList(new String[]{"444323","456223574"}));
		editorial = new Editorial();
		editorial.setTelefonos(telefonos);
		editorial.setFechaAlta(new Date());
		editorial.setNombre("Editorial de prueba");
		editorial.setEmail("prueba@gmail.com");
		EditorialDAO.create(editorial);
		id = editorial.getId();
	}

	@Test
	public void testFind() {
		assertEquals(editorial.getId(),EditorialDAO.find(id).getId());
	}

	@Test
	public void testUpdate() {
		editorial.setNombre("Editorial Modificada");
		EditorialDAO.update(editorial);
		assertEquals("Editorial Modificada",EditorialDAO.find(id).getNombre());
	}

	@Ignore //No se usa
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Ignore //Probado en otro método
	public void testCreate() {
		fail("Not yet implemented");
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		EditorialDAO.delete(id);
	}
	
}
