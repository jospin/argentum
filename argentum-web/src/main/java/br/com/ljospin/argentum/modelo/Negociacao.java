package br.com.ljospin.argentum.modelo;

import java.io.Serializable;
import java.util.Calendar;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("negociacao")
public final class Negociacao implements Comparable<Negociacao>, Serializable{
	private final double preco;
	private final int quantidade;
	private final Calendar data;
 
	public Negociacao(double preco, int quantidade, Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("Data não pode ser null");
		}
		if (quantidade == 0) {
			throw new IllegalArgumentException("Quantidade não pode vir zerada");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) this.data.clone();
	}

	/**
	 * Método para renovar o volume da negociação
	 * 
	 * @author Lucien Carbonare Jospin <lucien.carbonare@gmail.com>
	 * @return double Volume
	 */
	public double getVolume() {
		return quantidade * preco;
	}

	public boolean isMesmoDia(Calendar outraData) {

		return this.data.get(Calendar.DAY_OF_MONTH) == outraData.get(Calendar.DAY_OF_MONTH)
				&& this.data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR)
				&& this.data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH);
	}

	@Override
	public int compareTo(Negociacao outraNegociacao) {
		if(this.getData().before(outraNegociacao.getData()) ) {
			return -1;
		} else if(this.getData().after(outraNegociacao.getData())) {
			return 1;
		}
		return 0;
	}
}
