package br.com.tgolopes.sociotorcedor.mock;

import java.util.Calendar;

import br.com.tgolopes.controller.request.SocioTorcedorRequest;

public class SocioTorcedorRequestMock {

	public static SocioTorcedorRequest getSocioTorcedorRequest(){
		SocioTorcedorRequest socioTorcedorRequest = new SocioTorcedorRequest();
		socioTorcedorRequest.setNome("Bruno Souza");
		socioTorcedorRequest.setEmail("bruno@hotmail.com");
		socioTorcedorRequest.setDataNascimento(Calendar.getInstance());
		socioTorcedorRequest.setTimeCoracao("Ituano");
		return socioTorcedorRequest;
	}

}
