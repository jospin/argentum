package br.com.ljospin.argentum.indicadores;

import br.com.ljospin.argentum.modelo.Candle;
import br.com.ljospin.argentum.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador {

	private Indicador outroIndicador;

	public MediaMovelSimples(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.ljospin.argentum.indicadores.Indicador#calcula(int,
	 * br.com.ljospin.argentum.modelo.SerieTemporal)
	 */
	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		for (int i = posicao; i > posicao - 3; i--) {
			soma += this.outroIndicador.calcula(i, serie);
		}
		return soma / 3;
	}

	public String toString() {
		return "MMS de " + this.outroIndicador.toString();
	}

}
