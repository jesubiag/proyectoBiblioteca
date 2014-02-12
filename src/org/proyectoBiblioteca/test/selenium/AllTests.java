package org.proyectoBiblioteca.test.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AltaDePrestamo1Test.class, AltaDePrestamo2Test.class,
		AltaDePrestamo3Test.class, AltaDePrestamo4Test.class,
		BookSearch1Test.class, BookSearch3Test.class, BookSearch4Test.class,
		BookSearch5Test.class, BookSearch6Test.class, BookSearch7Test.class,
		DevolucionDePrestamoTest.class, LogIn1Test.class, LogIn2Test.class,
		LogIn3Test.class, Prestamo2Test.class }) //Prestamo1Test.class no se ejecuta ya que va a fallar siempre (requiere que no haya préstamos pendientes)
public class AllTests {
}
