package br.com.ljospin.argentum.modelo;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CandleFactory {

	/**
	 * Método para construir um candlestick pela data
	 * 
	 * @param data
	 * @author Lucien Carbonare Jospin <lucien.carbonare@gmail.com>
	 * @param negociacoes
	 * @return
	 */
	public Candle criaCandleParaData(Calendar data, List<Negociacao> negociacoes) {
		double minimo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double maximo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double volume = 0;

		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();
			double preco = negociacao.getPreco();
			if (preco > maximo) {
				maximo = preco;
			} else if (preco < minimo) {
				minimo = preco;
			}
		}
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() - 1).getPreco();

		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}

	/**
	 * Método para constroir o Candlestick de cada dia através de um lista de
	 * negociações
	 * 
	 * @param todasNegociacoes
	 * @author Lucien Carbonare Jospin <lucien.carbonare@gmail.com>
	 * @return List<Candlestick>
	 */
	public List<Candle> constroiCandles(List<Negociacao> todasNegociacoes) {
		List<Candle> candles = new ArrayList<Candle>();

		Collections.sort(todasNegociacoes);

		List<Negociacao> negociacaoDoDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();

		for (Negociacao negociacao : todasNegociacoes) {
			if (!negociacao.isMesmoDia(dataAtual)) {
				criaEGuardaCandle(candles, negociacaoDoDia, dataAtual);
				negociacaoDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacaoDoDia.add(negociacao);
		}
		criaEGuardaCandle(candles, negociacaoDoDia, dataAtual);
		return candles;
	}

	private void criaEGuardaCandle(List<Candle> candles, List<Negociacao> negociacaoDoDia, Calendar dataAtual) {
		Candle candleDoDia = this.criaCandleParaData(dataAtual, negociacaoDoDia);
		candles.add(candleDoDia);
	}
}
