package br.com.ljospin.argentum.bean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.ljospin.argetum.ws.ClienteWebService;
import br.com.ljospin.argentum.grafico.GeradorModeloGrafico;
import br.com.ljospin.argentum.indicadores.Indicador;
import br.com.ljospin.argentum.indicadores.IndicadorFactory;
import br.com.ljospin.argentum.indicadores.IndicadorFechamento;
import br.com.ljospin.argentum.indicadores.MediaMovelPonderada;
import br.com.ljospin.argentum.indicadores.MediaMovelSimples;
import br.com.ljospin.argentum.modelo.Candle;
import br.com.ljospin.argentum.modelo.CandleFactory;
import br.com.ljospin.argentum.modelo.Negociacao;
import br.com.ljospin.argentum.modelo.SerieTemporal;

@ManagedBean
@ViewScoped
/**
 * Classe Bean responsável por fazer a interação com a view do JSF da index.xhtml
 * @author Lucien Jospin <lucien.carbonare@gmail.com>
 * @category Bean
 */
public class ArgentumBean implements Serializable {
	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String nomeMedia;
	private String nomeIndicadorBase;

	public ArgentumBean() {
		this.negociacoes = new ClienteWebService().getNegociacao();
		geraGrafico();
	}

	/**
	 * Gerador de gráfico de acordo com as ações dos botões
	 * @author Lucien Jospin <lucien.carbonare@gmail.com>
	 * @return void
	 */
	public void geraGrafico() {
		
		if (this.nomeIndicadorBase == null && this.nomeMedia == null) {
			this.nomeIndicadorBase = "IndicadorFechamento";
			this.nomeMedia = "MediaMovelSimples";
		}
		
		List<Candle> candles = new CandleFactory().constroiCandles(this.negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		IndicadorFactory fabrica = new IndicadorFactory(this.nomeMedia, this.nomeIndicadorBase);
		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao());
		geradorGrafico.plotaIndicador(fabrica.defineIndicador());
		this.modeloGrafico = geradorGrafico.getModeloGrafico();		
	}

	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}

	public ChartModel getModeloGrafico() {
		return this.modeloGrafico;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

}
