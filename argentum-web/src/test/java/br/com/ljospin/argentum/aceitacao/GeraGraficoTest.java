package br.com.ljospin.argentum.aceitacao;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

public class GeraGraficoTest {

	private static final String URL = "http://localhost:8081/argentum-web/index.xhtml";
	private WebDriver driver;
	
	@Before
	public void setUp() {
		this.driver = new FirefoxDriver();
	}
	
	@Test
	public void aoGerarGraficoSemTituloUmaMensagemApresentada() {
		this.driver.navigate().to(URL);
		
		WebElement titulo = this.driver.findElement(By.id("dadosGrafico:titulo"));
		titulo.sendKeys();
		titulo.submit();
		
		boolean existeMensagem = driver.getPageSource().contains("Erro de Validação");
		assertTrue(existeMensagem);
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}

}
