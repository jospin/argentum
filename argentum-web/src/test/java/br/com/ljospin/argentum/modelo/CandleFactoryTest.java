package br.com.ljospin.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class CandleFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();

		Negociacao n1 = new Negociacao(40.5, 100, hoje);
		Negociacao n2 = new Negociacao(45.0, 100, hoje);
		Negociacao n3 = new Negociacao(39.8, 100, hoje);
		Negociacao n4 = new Negociacao(42.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(n1, n2, n3, n4);

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.criaCandleParaData(hoje, negociacoes);
		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(42.3, candle.getFechamento(), 0.00001);
		assertEquals(39.8, candle.getMinimo(), 0.00001);
		assertEquals(45.0, candle.getMaximo(), 0.00001);
		assertEquals(16760.0, candle.getVolume(), 0.00001);

	}

	@Test
	public void semNegociacaoGeraCandleComZero() {
		Calendar hoje = Calendar.getInstance();

		List<Negociacao> negociacoes = Arrays.asList();

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.criaCandleParaData(hoje, negociacoes);

		assertEquals(0.0, candle.getVolume(), 0.00001);
		assertEquals(0.0, candle.getMinimo(), 0.00001);
		assertEquals(0.0, candle.getMaximo(), 0.00001);
		assertEquals(0.0, candle.getAbertura(), 0.00001);
		assertEquals(0.0, candle.getFechamento(), 0.00001);
	}

	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
		Calendar hoje = Calendar.getInstance();

		Negociacao n1 = new Negociacao(40.5, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(n1);

		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.criaCandleParaData(hoje, negociacoes);

		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(40.5, candle.getFechamento(), 0.00001);
		assertEquals(40.5, candle.getMinimo(), 0.00001);
		assertEquals(40.5, candle.getMaximo(), 0.00001);
		assertEquals(4050, candle.getVolume(), 0.00001);
	}

	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {
		Calendar hoje = Calendar.getInstance();

		Negociacao n1 = new Negociacao(40.5, 100, hoje);
		Negociacao n2 = new Negociacao(41.5, 100, hoje);
		Negociacao n3 = new Negociacao(45.5, 100, hoje);
		Negociacao n4 = new Negociacao(38.0, 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao n5 = new Negociacao(38.1, 100, amanha);
		Negociacao n6 = new Negociacao(38.9, 100, amanha);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao n7 = new Negociacao(40.4, 100, depois);
		Negociacao n8 = new Negociacao(40.7, 100, depois);

		List<Negociacao> negociacoes = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8);

		CandleFactory fabrica = new CandleFactory();
		List<Candle> candles = fabrica.constroiCandles(negociacoes);

		assertEquals(3, candles.size());
		assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		assertEquals(38.0, candles.get(0).getFechamento(), 0.00001);
		assertEquals(38.1, candles.get(1).getAbertura(), 0.00001);
		assertEquals(38.9, candles.get(1).getFechamento(), 0.00001);
		assertEquals(40.4, candles.get(2).getAbertura(), 0.00001);
		assertEquals(40.7, candles.get(2).getFechamento(), 0.00001);

	}
	
	@Test
	public void ordenaAoConstruirCandlesComNegociacoesForaDeOrdem() {
		Calendar hoje = Calendar.getInstance();

		Negociacao n1 = new Negociacao(40.5, 100, hoje);
		Negociacao n2 = new Negociacao(41.5, 100, hoje);
		Negociacao n3 = new Negociacao(45.5, 100, hoje);
		Negociacao n4 = new Negociacao(38.0, 100, hoje);

		Calendar ontem = (Calendar) hoje.clone();
		ontem.add(Calendar.DAY_OF_MONTH, -1);

		Negociacao n5 = new Negociacao(38.1, 100, ontem);
		Negociacao n6 = new Negociacao(38.9, 100, ontem);

		Calendar antes = (Calendar) ontem.clone();
		antes.add(Calendar.DAY_OF_MONTH, -1);

		Negociacao n7 = new Negociacao(40.4, 100, antes);
		Negociacao n8 = new Negociacao(40.7, 100, antes);

		List<Negociacao> negociacoes = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8);

		CandleFactory fabrica = new CandleFactory();
		List<Candle> candles = fabrica.constroiCandles(negociacoes);

		assertEquals(3, candles.size());
		assertEquals(40.4, candles.get(0).getAbertura(), 0.00001);
		assertEquals(40.7, candles.get(0).getFechamento(), 0.00001);
		assertEquals(38.1, candles.get(1).getAbertura(), 0.00001);
		assertEquals(38.9, candles.get(1).getFechamento(), 0.00001);
		assertEquals(40.5, candles.get(2).getAbertura(), 0.00001);
		assertEquals(38.0, candles.get(2).getFechamento(), 0.00001);
		assertEquals(38.0, candles.get(2).getMinimo(), 0.00001);
		assertEquals(45.5, candles.get(2).getMaximo(), 0.00001);

	}

}
