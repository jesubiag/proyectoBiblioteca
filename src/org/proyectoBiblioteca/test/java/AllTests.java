package org.proyectoBiblioteca.test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AutorDAOTest.class, DiaNoHabilDAOTest.class,
		EditorialDAOTest.class, EjemplarDAOTest.class, LibroDAOTest.class,
		LocalidadDAOTest.class, MovimientoDAOTest.class, PrestamoDAOTest.class,
		SocioDAOTest.class, SuspensionDAOTest.class })
public class AllTests {

}
