package org.proyectoBiblioteca.test.java;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.proyectoBiblioteca.dao.LocalidadDAO;
import org.proyectoBiblioteca.dao.ProvinciaDAO;
import org.proyectoBiblioteca.dao.SocioDAO;
import org.proyectoBiblioteca.domain.Direccion;
import org.proyectoBiblioteca.domain.Localidad;
import org.proyectoBiblioteca.domain.Socio;
import org.proyectoBiblioteca.enums.EstadoSocio;

public class SocioDAOTest {

	private static Socio socio;
	private static long id;
	private static long idLocalidad;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Creo localidad
		Localidad localidad = new Localidad(ProvinciaDAO.find("Buenos Aires"),"Localidad inexistente");
		idLocalidad = localidad.getId();
		//Creo direccion
		Direccion direccion = new Direccion("Calle Falsa","2804",125,localidad);
		socio = new Socio(12345677,"The","Doctor","doctor@who.com","445789",direccion);
		SocioDAO.create(socio);
		id = socio.getId();
		idLocalidad = localidad.getId();
	}

	@Test
	public void testFind() {
		assertEquals(socio.getId(),SocioDAO.find(id).getId());
	}
	
	@Test
	public void testUpdate() {
		socio.setEstado(EstadoSocio.suspendido);
		SocioDAO.update(socio);
		assertEquals(EstadoSocio.suspendido,SocioDAO.find(id).getEstado());
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
		SocioDAO.delete(id);
		LocalidadDAO.delete(idLocalidad);
	}

}
