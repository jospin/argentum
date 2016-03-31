package br.com.ljospin.argentum.indicadores;

import java.lang.reflect.Constructor;

public class IndicadorFactory {
	
	private final String nomeMedia;
	private final String nomeIndicadorBase;

	public IndicadorFactory(String nomeMedia, String nomeIndicadorBase) {
		if (nomeIndicadorBase == null || nomeMedia == null) {
			throw new IllegalArgumentException("Argumentos passados n�o podem ser nulos");
		}
		this.nomeMedia = nomeMedia;
		this.nomeIndicadorBase = nomeIndicadorBase;	
	}
	
	/**
	 * M�todo respons�vel por definir o indicador de acordo com a intera��o dos bot�es da interface.
	 * @return Indicador
	 * @exception RuntimeException
	 */
	public Indicador defineIndicador() {
		if (nomeIndicadorBase == null || nomeMedia == null) {
			return new MediaMovelSimples(new IndicadorFechamento());
		}
		String pacote = "br.com.ljospin.argentum.indicadores.";
		try {
			Class<?> classeIndicadorBase = Class.forName(pacote+this.nomeIndicadorBase);
			Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();
			
			Class<?> classMedia = Class.forName(pacote+this.nomeMedia);
			Constructor<?> construtorMedia = classMedia.getConstructor(Indicador.class);
			Indicador indicador = (Indicador) construtorMedia.newInstance(indicadorBase);
			return indicador;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
