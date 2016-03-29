package br.com.ljospin.argentum.reader;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.ljospin.argentum.modelo.Negociacao;

public class LeitorXML {
	public List<Negociacao> carrega(InputStream input) {
		XStream stream = new XStream(new DomDriver());
		//stream.autodetectAnnotations(true);
		stream.alias("negociacao", Negociacao.class);
		return (List<Negociacao>) stream.fromXML(input);
	}
}
