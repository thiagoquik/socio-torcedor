package br.com.tgolopes.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tgolopes.controller.facade.SocioTorcedorFacade;
import br.com.tgolopes.controller.request.SocioTorcedorRequest;
import br.com.tgolopes.controller.response.SocioTorcedorResponse;
import br.com.tgolopes.exception.EmailJaCadastrado;
import br.com.tgolopes.exception.SocioTorcedorNaoEncontradoException;
import br.com.tgolopes.exception.TimeCoracaoNaoEncontrado;
import br.com.tgolopes.integration.dto.CampanhaRequestDTO;
import br.com.tgolopes.integration.dto.CampanhaResponseDTO;
import br.com.tgolopes.integration.dto.TorcedorCampanhaRequestDTO;
import br.com.tgolopes.integration.dto.TorcedorCampanhaResponseDTO;


@RestController
@RequestMapping(value = "v1/torcedor")
public class SocioTorcedorController {

	@Autowired
	private SocioTorcedorFacade socioTorcedorFacade;
	
	@ApiOperation(value = "Salva um Socio Torcedor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public SocioTorcedorResponse salvar(@RequestBody @Valid SocioTorcedorRequest socioTorcedorRequest) throws EmailJaCadastrado {
		return this.socioTorcedorFacade.salvar(socioTorcedorRequest);
	}
	
	@ApiOperation(value = "Altera um Socio Torcedor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public SocioTorcedorResponse alterar(@PathVariable Long id, @RequestBody @Valid SocioTorcedorRequest socioTorcedorRequest) throws EmailJaCadastrado, SocioTorcedorNaoEncontradoException {
		return this.socioTorcedorFacade.alterar(id, socioTorcedorRequest);
	}

	@ApiOperation(value = "Exclui um Socio Torcedor", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE , produces = {MediaType.APPLICATION_JSON_VALUE})
	public void excluir(@PathVariable Long id) throws SocioTorcedorNaoEncontradoException {
		this.socioTorcedorFacade.excluir(id);
	}
	
	@ApiOperation(value = "Consulta um Socio Torcedor por id", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public SocioTorcedorResponse pesquisarId(@PathVariable Long id) throws SocioTorcedorNaoEncontradoException {
		return this.socioTorcedorFacade.buscar(id);
	}
	
	@ApiOperation(value = "Consulta todos os Socios Torcedores", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<SocioTorcedorResponse> exibirTodos() {
		return socioTorcedorFacade.buscarTodos();
	}
	
	@ApiOperation(value = "Consulta uma Campanha da API por id", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha/{idCampanha}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CampanhaResponseDTO buscarCampanhaPorId(@PathVariable Long idCampanha) {
		return socioTorcedorFacade.buscarCampanhaPorId(idCampanha);
	}
	
	@ApiOperation(value = "Consulta Campanhas Vigentes por Time da API", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha/time/{nome}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CampanhaResponseDTO[]> buscarCampanhasVigentesPorTime(@PathVariable String nome) throws TimeCoracaoNaoEncontrado {
		return socioTorcedorFacade.buscarCampanhasVigentesPorTime(nome);
	}
	
	@ApiOperation(value = "Consulta Campanhas Vigentes da API", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CampanhaResponseDTO[]> buscarCampanhasVigentes() {
		return socioTorcedorFacade.buscarCampanhasVigentes();
	}
	
	@ApiOperation(value = "Cria uma Campanha da API", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CampanhaResponseDTO criarCampanha(@RequestBody @Valid CampanhaRequestDTO campanhaRequestDTO) {
		return this.socioTorcedorFacade.salvarCampanha(campanhaRequestDTO);
	}
	
	@ApiOperation(value = "Faz Associacao do Torcedor com a Campanha da API", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/associacao", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TorcedorCampanhaResponseDTO associarCampanha(@RequestBody @Valid TorcedorCampanhaRequestDTO torcedorCampanhaRequestDTO) throws SocioTorcedorNaoEncontradoException {
		return this.socioTorcedorFacade.associarCampanha(torcedorCampanhaRequestDTO);
	}
}