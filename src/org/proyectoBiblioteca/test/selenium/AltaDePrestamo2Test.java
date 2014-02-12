package org.proyectoBiblioteca.test.selenium;

import java.util.Date;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.proyectoBiblioteca.utils.Utilidades;

public class AltaDePrestamo2Test {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://bibliotecautn.sytes.net";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    Utilidades.seleniumLogin(driver, baseUrl);
  }

  @Test
  public void testAltaDePrestamo2() throws Exception {
	driver.get(baseUrl + "/proyectoBiblioteca/Prestamos?action=new&id=1"); //El 1 es el id del ejemplar, debe estar disponible para que funcione la prueba
	new Select(driver.findElement(By.name("prestamoLocal"))).selectByVisibleText("No");
	driver.findElement(By.name("idSocio")).clear();
    driver.findElement(By.name("idSocio")).sendKeys("1"); //id de nico, debe tener dos préstamos domiciliarios para que funcione
    driver.findElement(By.id("fecha")).clear();
    driver.findElement(By.id("fecha")).sendKeys(Utilidades.getSimpleDate(new Date())); //pongo la fecha de hoy
    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
    assertEquals("Préstamo no creado. El socio ingresado ya posee demasiados préstamos activos.", driver.findElement(By.cssSelector("div.alert.alert-info")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
