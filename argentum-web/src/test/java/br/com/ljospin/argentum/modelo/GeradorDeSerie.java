package br.com.ljospin.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GeradorDeSerie {
	
	public static SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<Candle>();
		for (double d : valores) {
			candles.add(new Candle(d-1,d,d,d,1000,Calendar.getInstance()));
		}
		return new SerieTemporal(candles);
	}

}
