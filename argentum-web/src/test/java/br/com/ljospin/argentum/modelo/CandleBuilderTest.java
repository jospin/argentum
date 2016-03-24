package br.com.ljospin.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;
import org.junit.Test;


public class CandleBuilderTest {

	@Test(expected=IllegalStateException.class)
	public void geracaoDeCandleComArgumentosImcompletos() {
		new CandleBuilder().comAbertura(10).comMinimo(5).geraCandle();
	}
	@Test
	public void geracaoDeCandleComArgumentosCompletos() {
		Candlestick candle = new CandleBuilder()
									.comAbertura(10)
									.comMinimo(5)
									.comMaximo(15)
									.comFechamento(15)
									.comVolume(1000)
									.comData(Calendar.getInstance())
									.geraCandle();
		
		assertEquals(10, candle.getAbertura(), 0.00001);
		assertEquals(15,candle.getFechamento(), 0.00001);		
		assertEquals(5, candle.getMinimo(), 0.00001);
		assertEquals(15, candle.getMaximo(), 0.00001);
		assertEquals(1000,candle.getVolume(),0.00001);
	}

}
