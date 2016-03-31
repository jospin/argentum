package br.com.ljospin.argentum.indicadores;

import br.com.ljospin.argentum.modelo.SerieTemporal;

public class IndicadorAbertura implements Indicador {

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		return serie.getCandles(posicao).getAbertura();
	}

	public String toString() {
		return "Abertura";
	}

}
