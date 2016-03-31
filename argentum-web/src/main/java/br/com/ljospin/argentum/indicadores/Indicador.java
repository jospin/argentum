package br.com.ljospin.argentum.indicadores;

import br.com.ljospin.argentum.modelo.SerieTemporal;

public interface Indicador {

	abstract double calcula(int posicao, SerieTemporal serie);

}