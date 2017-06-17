package br.com.tgolopes.controller.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tgolopes.controller.response.TimeCoracaoResponse;
import br.com.tgolopes.exception.TimeCoracaoNaoEncontrado;
import br.com.tgolopes.service.TimeCoracaoService;

@Component
public class TimeCoracaoFacade {
	@Autowired
	private TimeCoracaoService timeCoracaoService;
	
	public TimeCoracaoResponse buscar(Long id) throws TimeCoracaoNaoEncontrado {
		return Optional.ofNullable(timeCoracaoService.pesquisarPorId(id)).map(TimeCoracaoResponse::new).orElseThrow(() -> new TimeCoracaoNaoEncontrado());
	}
	
	public List<TimeCoracaoResponse> buscarTodas() {
		return timeCoracaoService.pesquisarTodas().stream().map(TimeCoracaoResponse::new).collect(Collectors.toList());
	}
	
	public TimeCoracaoResponse buscarTimePorNome(String nome) throws TimeCoracaoNaoEncontrado {
		return Optional.ofNullable(timeCoracaoService.buscarTimePorNome(nome)).map(TimeCoracaoResponse::new).orElseThrow(() -> new TimeCoracaoNaoEncontrado());
	}
}