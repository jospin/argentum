 package br.com.ljospin.argentum.modelo;

import java.util.Calendar;
import static org.junit.Assert.*;

import org.junit.Test;

public class CandleTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		new Candle(10, 20, 20, 10, 10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dataNaoPodeSerNull() {
		new Candle(10, 20, 20, 21, 1000, null);
	}
	
	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {
		Candle candle = new Candle(20, 20, 10, 20, 1000, Calendar.getInstance());
		assertEquals(true, candle.isAlta());
	}

}
