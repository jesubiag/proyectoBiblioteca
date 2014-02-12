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

public class BookSearch3Test {
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
  public void testBookSearch3() throws Exception {
    driver.get(baseUrl + "/proyectoBiblioteca/");
    new Select(driver.findElement(By.name("type"))).selectByVisibleText("Título");
    driver.findElement(By.name("search")).clear();
    driver.findElement(By.name("search")).sendKeys("Harry");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    assertEquals("Libros Encontrados", driver.findElement(By.cssSelector("div.panel-heading")).getText());
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
