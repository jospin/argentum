package br.com.ljospin.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoImutavel() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(10, 5, c);
		
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNull() {
		new Negociacao(10.5, 100, null);
	}
	
	@Test
	public void mesmoMilesegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		
		Negociacao negociacao = new Negociacao(40.5, 100, agora);
		assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}
	
	@Test
	public void comHoraDiferenteEhMesmoDia() {
		Calendar manha = new GregorianCalendar(2016,03,22,8,30);
		Calendar tarde = new GregorianCalendar(2016,03,22,16,30);
		
		Negociacao negociacao = new Negociacao(40.5,100,manha);
		assertTrue(negociacao.isMesmoDia(tarde));
	}
	
	@Test
	public void  mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {
		Calendar mars = new GregorianCalendar(2016,03,22,8,10);
		Calendar january = new GregorianCalendar(2016,01,22,8,7);
		
		Negociacao negociacao = new Negociacao(40.5,100,mars);
		assertFalse(negociacao.isMesmoDia(january));
	}
	
	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia() {
		Calendar m2015 = new GregorianCalendar(2015,03,22,8,10);
		Calendar m2016 = new GregorianCalendar(2016,03,22,8,10);
		
		Negociacao negociacao = new Negociacao(40.5,100,m2016);
		assertFalse(negociacao.isMesmoDia(m2015));
	}
}
