package br.com.ljospin.argentum.indicadores;

import br.com.ljospin.argentum.modelo.Candle;
import br.com.ljospin.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {
	
	private Indicador outroIndicador;

	public MediaMovelPonderada(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
	}
	
	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		int fator = 3;
		for (int i = posicao; i > posicao - 3; i--) {
			soma += this.outroIndicador.calcula(i, serie) * fator;
			fator--;
		}
		return soma/6;
	}
	
	public String toString() {
		return "MMP de " + this.outroIndicador.toString();
	}	
}
