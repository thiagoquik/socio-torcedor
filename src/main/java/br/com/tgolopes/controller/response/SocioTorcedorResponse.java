package br.com.tgolopes.controller.response;

import java.util.Calendar;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.tgolopes.controller.util.CalendarSerializer;
import br.com.tgolopes.entity.SocioTorcedor;

public class SocioTorcedorResponse {

	private Long codigoSocioTorcedor;
	private String nome;
	private String email;
	@JsonSerialize(using = CalendarSerializer.class)
	private Calendar dataNascimento;
	private String timeCoracao;
	
	public SocioTorcedorResponse(SocioTorcedor socioTorcedor){
		codigoSocioTorcedor = socioTorcedor.getId();
		nome = socioTorcedor.getNome();
		email = socioTorcedor.getEmail();
		dataNascimento = socioTorcedor.getDataNascimento();
		timeCoracao = socioTorcedor.getTimeCoracao();
	}
	
	public Long getCodigoSocioTorcedor() {
		return codigoSocioTorcedor;
	}

	public void setCodigoSocioTorcedor(Long codigoSocioTorcedor) {
		this.codigoSocioTorcedor = codigoSocioTorcedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTimeCoracao() {
		return timeCoracao;
	}

	public void setTimeCoracao(String timeCoracao) {
		this.timeCoracao = timeCoracao;
	}
}