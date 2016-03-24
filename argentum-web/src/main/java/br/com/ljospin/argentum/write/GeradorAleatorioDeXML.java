package br.com.ljospin.argentum.write;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.ljospin.argentum.modelo.Negociacao;

public class GeradorAleatorioDeXML {

	public static void main(String[] args) {
		Calendar data = Calendar.getInstance();
		Random rand = new Random(123);
		List<Negociacao> negociacoes = new ArrayList<>();
		
		double valor = 40;
		int quantidade = 1000;
		
		for(int dias = 0; dias < 30; dias ++) {
			int quantidadeDeNegociacoesDoDia = rand.nextInt(20);
			
			for (int negociacao = 0; negociacao < quantidadeDeNegociacoesDoDia; negociacao++) {
				
				// no máximo sobe ou cai R$1,00 e nao baixa além de R$5,00
				valor += (rand.nextInt(200) + 100) / 100.0;
				if (valor < 5.0) {
					valor = 5.0;
				}
				
				quantidade += 1000 + rand.nextInt(500);
				
				Negociacao n = new Negociacao(valor, quantidade, data);
				negociacoes.add(n);
			}
			data = (Calendar) data.clone();
			data.add(Calendar.DAY_OF_YEAR, 1);
		}
		XStream stream = new XStream(new DomDriver());
		stream.autodetectAnnotations(true);
		stream.setMode(XStream.NO_REFERENCES);
		
		try {
			PrintStream out = new PrintStream(new File("negociacao3.xml"));
			out.println(stream.toXML(negociacoes));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

}
