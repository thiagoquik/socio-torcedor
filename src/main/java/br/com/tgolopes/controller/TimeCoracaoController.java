package br.com.tgolopes.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tgolopes.controller.facade.TimeCoracaoFacade;
import br.com.tgolopes.controller.response.TimeCoracaoResponse;
import br.com.tgolopes.exception.TimeCoracaoNaoEncontrado;

@RestController
@RequestMapping(value = "v1/time")
public class TimeCoracaoController {

	@Autowired
	private TimeCoracaoFacade timeCoracaoFacade;
	
	@ApiOperation(value = "Consulta um Time pelo id", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TimeCoracaoResponse buscar(@PathVariable Long id) throws TimeCoracaoNaoEncontrado {
		return timeCoracaoFacade.buscar(id);
	}
	
	@ApiOperation(value = "Consulta todos os times", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TimeCoracaoResponse> buscarTodos() {
		return timeCoracaoFacade.buscarTodas();
	}
	
	@ApiOperation(value = "Consulta um Time pelo nome", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TimeCoracaoResponse buscarTimePorNome(@PathVariable String nome) throws TimeCoracaoNaoEncontrado {
		return timeCoracaoFacade.buscarTimePorNome(nome);
	}
}