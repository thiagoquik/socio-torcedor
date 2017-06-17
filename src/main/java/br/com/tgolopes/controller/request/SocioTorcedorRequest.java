package br.com.tgolopes.controller.request;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import br.com.tgolopes.entity.SocioTorcedor;

public class SocioTorcedorRequest {

	@NotNull(message = "O Nome do Sócio Torcedor é obrigatório!")
	private String nome;
	
	@NotNull(message = "O Email do Sócio Torcedor é obrigatório!")
	private String email;
	
	@NotNull(message = "A Data de Nascimento do Sócio Torcedor é obrigatória!")
	private Calendar dataNascimento;
	
	@NotNull(message = "O Time do Coração do Sócio Torcedor é obrigatório!")
	private String timeCoracao;
	
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
	
	public SocioTorcedor toSocioTorcedor(){
		SocioTorcedor socioTorcedor = new SocioTorcedor();
		socioTorcedor.setNome(nome);
		socioTorcedor.setEmail(email);
		socioTorcedor.setDataNascimento(dataNascimento);
		socioTorcedor.setTimeCoracao(timeCoracao);
		return socioTorcedor;
	}
}