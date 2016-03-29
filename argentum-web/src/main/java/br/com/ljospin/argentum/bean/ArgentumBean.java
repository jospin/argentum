package br.com.ljospin.argentum.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.argentum.ws.ClienteWebService;
import br.com.ljospin.argentum.modelo.Negociacao;

@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable {
	private List<Negociacao> negociacoes;

	public ArgentumBean() {
		this.negociacoes = new ClienteWebService().getNegociacao();
	}

	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}
}
