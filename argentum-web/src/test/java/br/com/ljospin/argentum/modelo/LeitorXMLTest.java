package br.com.ljospin.argentum.modelo;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.ljospin.argentum.reader.LeitorXML;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list>" +
				"<negociacao>" +
				"<preco>43.5</preco>" +
				"<quantidade>1000</quantidade>" +
				"<data>" +
				"<time>1322233344455</time>" +
				"</data>" +
				"</negociacao>" +
				"</list>";
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		assertEquals(1, negociacoes.size());
		assertEquals(43.5, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(43500.0, negociacoes.get(0).getVolume(), 0.00001);
		
	}
	
}
