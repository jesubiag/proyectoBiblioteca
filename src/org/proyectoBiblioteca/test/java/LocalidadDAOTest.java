package org.proyectoBiblioteca.test.java;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.proyectoBiblioteca.dao.LocalidadDAO;
import org.proyectoBiblioteca.dao.ProvinciaDAO;
import org.proyectoBiblioteca.domain.Localidad;

public class LocalidadDAOTest {
	private static Localidad localidad;
	private static long id;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		localidad = new Localidad(ProvinciaDAO.find("Buenos Aires"),"Localidad de Prueba");
		LocalidadDAO.create(localidad);
		id = localidad.getId();
	}

	@Test
	public void testFind() {
		assertEquals(localidad.getId(),LocalidadDAO.find(id).getId());
	}

	@Test
	public void testUpdate() {
		localidad.setNombre("Localidad modificada");
		LocalidadDAO.update(localidad);
		assertEquals("Localidad modificada",LocalidadDAO.find(id).getNombre());
	}

	@Test
	public void testFindByFields() {
		Localidad localidadAux = LocalidadDAO.findByFields(localidad.getProvincia().getNombre(), localidad.getNombre());
		assertEquals(localidadAux.getId(),LocalidadDAO.find(id).getId());
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
		LocalidadDAO.delete(id);
	}
}
