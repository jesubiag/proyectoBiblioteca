package org.proyectoBiblioteca.test.java;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.proyectoBiblioteca.dao.DiaNoHabilDAO;
import org.proyectoBiblioteca.domain.DiaNoHabil;

public class DiaNoHabilDAOTest {

	private static DiaNoHabil dia;
	private static long id;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, 1);
		dia = new DiaNoHabil(new Date(),cal.getTime(),"prueba");
		DiaNoHabilDAO.create(dia);
		id = dia.getId();
	}

	@Test
	public void testFind() {
		assertEquals(dia.getId(),DiaNoHabilDAO.find(id).getId());
	}

	@Test
	public void testUpdate() {
		dia.setMotivo("Motivo modificado");
		DiaNoHabilDAO.update(dia);
		assertEquals("Motivo modificado",DiaNoHabilDAO.find(id).getMotivo());
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
		DiaNoHabilDAO.delete(id);
	}

}
