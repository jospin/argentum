package br.com.ljospin.argentum.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class OlaMundoBean {

	private String mensagem = "Quem é você?";
	private String nome;

	public String getMensagem() {
		System.out.println("\n Chamou o get Message");
		return mensagem;
	}

	public String getNome() {
		System.out.println("\n Chamou o get Nome");
		return nome;
	}

	public void setNome(String nome) {
		System.out.println("\n Chamou o set Nome");
		this.nome = nome;
	}

	public void nomeFoiDigitado() {
		System.out.println("\n Chamou o botão");
	}
}
