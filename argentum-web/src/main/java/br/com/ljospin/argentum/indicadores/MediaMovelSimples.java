package br.com.ljospin.argentum.indicadores;

import br.com.ljospin.argentum.modelo.Candle;
import br.com.ljospin.argentum.modelo.SerieTemporal;

public class MediaMovelSimples {
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		for (int i = posicao; i > posicao - 3; i--) {
			soma += serie.getCandles(i).getFechamento();
		}
		return soma/3;
	}

}
