package org.proyectoBiblioteca.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.proyectoBiblioteca.dao.DiaNoHabilDAO;
import org.proyectoBiblioteca.domain.DiaNoHabil;

public class DiaNoHabilDAOTest {

	private static DiaNoHabil dia;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, 1);
		dia = new DiaNoHabil(new Date(),cal.getTime(),"prueba");
		DiaNoHabilDAO.create(dia);
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
