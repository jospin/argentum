package br.com.ljospin.argentum.indicadores;

import static org.junit.Assert.*;

import org.junit.Test;

public class IndicadorFactoryTeste {

	@Test(expected=IllegalArgumentException.class)
	public void testaPassandoArgumentoFaltante() {
		new IndicadorFactory("MediaMovelSimples", null);
	}
}
