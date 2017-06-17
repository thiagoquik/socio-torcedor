package br.com.tgolopes.integration.dto;

public class TorcedorCampanhaResponseDTO {
	
	private Long codigoAssociacao;
	private String email;
	private String nomeTime;
	private Long codigoCampanha;
	public Long getCodigoAssociacao() {
		return codigoAssociacao;
	}
	public void setCodigoAssociacao(Long codigoAssociacao) {
		this.codigoAssociacao = codigoAssociacao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNomeTime() {
		return nomeTime;
	}
	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}
	public Long getCodigoCampanha() {
		return codigoCampanha;
	}
	public void setCodigoCampanha(Long codigoCampanha) {
		this.codigoCampanha = codigoCampanha;
	}
}