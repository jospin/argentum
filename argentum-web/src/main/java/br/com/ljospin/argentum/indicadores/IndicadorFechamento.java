package br.com.ljospin.argentum.indicadores;

import br.com.ljospin.argentum.modelo.SerieTemporal;

public class IndicadorFechamento implements Indicador {

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		return serie.getCandles(posicao).getFechamento();
	}

	public String toString() {
		return "Fechamento";
	}

}
