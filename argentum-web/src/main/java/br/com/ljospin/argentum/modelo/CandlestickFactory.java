package br.com.ljospin.argentum.modelo;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CandlestickFactory {

	/**
	 * Método para construir um candlestick pela data
	 * 
	 * @param data
	 * @author Lucien Carbonare Jospin <lucien.carbonare@gmail.com>
	 * @param negociacoes
	 * @return
	 */
	public Candlestick criaCandleParaData(Calendar data, List<Negociacao> negociacoes) {
		double minimo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double maximo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double volume = 0;

		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();
			if (negociacao.getPreco() > maximo) {
				maximo = negociacao.getPreco();
			} else if (negociacao.getPreco() < minimo) {
				minimo = negociacao.getPreco();
			}
		}
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() - 1).getPreco();

		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}

	/**
	 * Método para constroir o Candlestick de cada dia através de um lista de
	 * negociações
	 * 
	 * @param todasNegociacoes
	 * @author Lucien Carbonare Jospin <lucien.carbonare@gmail.com>
	 * @return List<Candlestick>
	 */
	public List<Candlestick> constroiCandles(List<Negociacao> todasNegociacoes) {
		List<Candlestick> candles = new ArrayList<Candlestick>();
		
		Collections.sort(todasNegociacoes); 

		List<Negociacao> negociacaoDoDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();

		for (Negociacao negociacao : todasNegociacoes) {
			if (!negociacao.isMesmoDia(dataAtual)) {
				Candlestick candleDoDia = this.criaCandleParaData(dataAtual, negociacaoDoDia);
				candles.add(candleDoDia);
				negociacaoDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacaoDoDia.add(negociacao);
		}
		Candlestick candleDoDia = this.criaCandleParaData(dataAtual, negociacaoDoDia);
		candles.add(candleDoDia);
		return candles;
	}
}
