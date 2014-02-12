package org.proyectoBiblioteca.test.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.proyectoBiblioteca.utils.Utilidades;

public class DevolucionDePrestamoTest {
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
  public void testDevolucionDePrestamo() throws Exception {
    driver.get(baseUrl + "/proyectoBiblioteca/Prestamos?action=return&id=4");//El ejemplar id 4 fue prestado en la prueba de AltaDePrestamo4
    driver.findElement(By.name("nota")).clear();
    driver.findElement(By.name("nota")).sendKeys("observaciones");
    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
    assertEquals("Ejemplar devuelto correctamente.", driver.findElement(By.cssSelector("div.alert.alert-info")).getText());
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
