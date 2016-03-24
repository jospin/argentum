package br.com.ljospin.argentum.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Candlestick {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;

	public Candlestick(double abertura, double fechamento, double minimo, double maximo, double volume, Calendar data) {
		if (maximo < minimo) {
			throw new IllegalArgumentException("Valor m�ximo deve ser maior que m�nimo");
		}
		if (data == null) {
			throw new IllegalArgumentException("Data n�o pode ser nula");
		}
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}

	/**
	 * M�todo para verificar se Candlestick � de alta
	 * 
	 * @author Lucien Carbonare Jospin <lucien.carbonare@gmail.com>
	 * @return boolean Alta
	 */
	public boolean isAlta() {
		return this.abertura <= this.fechamento;
	}

	/**
	 * M�todo para verificar se Candlestick � de baixa
	 * 
	 * @author Lucien Carbonare Jospin <lucien.carbonare@gmail.com>
	 * @return boolean Alta
	 */
	public boolean isBaixa() {
		return this.abertura > this.fechamento;
	}

	@Override
	public String toString() {
		String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(this.data.getTime());
		String text = "[";
		text += "Abertura " + this.abertura;
		text += ", Fechamento " + this.fechamento;
		text += ", M�nimo " + this.minimo;
		text += ", M�ximo " + this.maximo;
		text += ", Volume " + this.volume;
		text += ", Data " + dataFormatada;
		text += "]";

		return text;
	}
}
