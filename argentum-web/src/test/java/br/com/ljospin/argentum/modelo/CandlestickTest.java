 package br.com.ljospin.argentum.modelo;

import java.util.Calendar;
import static org.junit.Assert.*;

import org.junit.Test;

public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		new Candlestick(10, 20, 20, 10, 10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dataNaoPodeSerNull() {
		new Candlestick(10, 20, 20, 21, 1000, null);
	}
	
	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {
		Candlestick candle = new Candlestick(20, 20, 10, 20, 1000, Calendar.getInstance());
		assertEquals(true, candle.isAlta());
	}

}
