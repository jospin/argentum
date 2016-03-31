package br.com.ljospin.argentum.indicadores;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.ljospin.argentum.modelo.GeradorDeSerie;
import br.com.ljospin.argentum.modelo.SerieTemporal;

public class MediaMovelPonderadaTest {

	@Test
	public void sequenciaSimplesDeCandles() throws Exception{
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		MediaMovelPonderada mms = new MediaMovelPonderada(new IndicadorFechamento());
		assertEquals(14.0/6, mms.calcula(2, serie), 0.00001);
		assertEquals(20.0/6, mms.calcula(3, serie), 0.00001);
		assertEquals(20.0/6, mms.calcula(4, serie), 0.00001);
		assertEquals(22.0/6, mms.calcula(5, serie), 0.00001);
		assertEquals(26.0/6, mms.calcula(6, serie), 0.00001);
		assertEquals(26.0/6, mms.calcula(7, serie), 0.00001);
		assertEquals(22.0/6, mms.calcula(8, serie), 0.00001);	
	}
	
	@Test
	public void sequenciaSimplesMediaAberturaDeCandles() throws Exception{
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		MediaMovelPonderada mms = new MediaMovelPonderada(new IndicadorAbertura());
		assertEquals(8.0/6, mms.calcula(2, serie), 0.00001);
		assertEquals(14.0/6, mms.calcula(3, serie), 0.00001);
		assertEquals(14.0/6, mms.calcula(4, serie), 0.00001);
		assertEquals(16.0/6, mms.calcula(5, serie), 0.00001);
	}


}
