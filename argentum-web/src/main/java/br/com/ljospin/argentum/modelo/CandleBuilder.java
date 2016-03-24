package br.com.ljospin.argentum.modelo;

import java.util.Calendar;

public class CandleBuilder {

	private double abertura;
	private double fechamento;
	private double minimo;
	private double maximo;
	private double volume;
	private int allSetters = 0;
	private Calendar data;

	public CandleBuilder comAbertura(double abertura) {
		this.abertura = abertura;
		this.allSetters++;
		return this;
	}

	public CandleBuilder comFechamento(double fechamento) {
		this.fechamento = fechamento;
		this.allSetters++;
		return this;
	}

	public CandleBuilder comMinimo(double minimo) {
		this.minimo = minimo;
		this.allSetters++;
		return this;
	}

	public CandleBuilder comMaximo(double maximo) {
		this.maximo = maximo;
		this.allSetters++;
		return this;
	}

	public CandleBuilder comData(Calendar data) {
		this.data = data;
		this.allSetters++;
		return this;
	}

	public CandleBuilder comVolume(double volume) {
		this.volume = volume;
		this.allSetters++;
		return this;
	}

	public Candlestick geraCandle() {
		if (this.allSetters != 6) {
			throw new IllegalStateException("Argumentos devem totalizar 6");
		}
		Candlestick candle = new Candlestick(this.abertura, this.fechamento, this.minimo, this.maximo, this.volume,
				this.data);
		return candle;
	}
}
