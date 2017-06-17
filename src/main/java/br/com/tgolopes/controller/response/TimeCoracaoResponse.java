package br.com.tgolopes.controller.response;

import br.com.tgolopes.entity.TimeCoracao;

public class TimeCoracaoResponse {
	private Long codigoTime;
	private String nome;
	
	public TimeCoracaoResponse(TimeCoracao timeCoracao){
		codigoTime = timeCoracao.getId();
		nome = timeCoracao.getNome();
	}

	public Long getCodigoTime() {
		return codigoTime;
	}

	public void setCodigoTime(Long codigoTime) {
		this.codigoTime = codigoTime;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}