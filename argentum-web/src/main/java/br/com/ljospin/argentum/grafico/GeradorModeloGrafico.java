package br.com.ljospin.argentum.grafico;

import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.ljospin.argentum.indicadores.Indicador;
import br.com.ljospin.argentum.indicadores.MediaMovelSimples;
import br.com.ljospin.argentum.modelo.SerieTemporal;

public class GeradorModeloGrafico {

	private SerieTemporal serie;
	private int comeco;
	private int fim;
	private LineChartModel modeloGrafico;

	public GeradorModeloGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
		this.modeloGrafico = new LineChartModel();
	}

	public void plotaIndicador(Indicador indicador) {
		LineChartSeries chartSeries = new LineChartSeries(indicador.toString());

		for (int i = this.comeco; i <= this.fim; i++) {
			double valor = indicador.calcula(i, this.serie);
			chartSeries.set(i, valor);
		}
		this.modeloGrafico.addSeries(chartSeries);
		this.modeloGrafico.setLegendPosition("w");
		this.modeloGrafico.setTitle("Indicador");
	}

	public ChartModel getModeloGrafico() {
		return this.modeloGrafico;
	}

}
