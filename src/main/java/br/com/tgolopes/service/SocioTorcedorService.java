package br.com.tgolopes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import br.com.tgolopes.controller.facade.TimeCoracaoFacade;
import br.com.tgolopes.controller.response.TimeCoracaoResponse;
import br.com.tgolopes.entity.SocioTorcedor;
import br.com.tgolopes.exception.EmailJaCadastrado;
import br.com.tgolopes.exception.SocioTorcedorNaoEncontradoException;
import br.com.tgolopes.exception.TimeCoracaoNaoEncontrado;
import br.com.tgolopes.integration.dto.CampanhaRequestDTO;
import br.com.tgolopes.integration.dto.CampanhaResponseDTO;
import br.com.tgolopes.integration.dto.TorcedorCampanhaRequestDTO;
import br.com.tgolopes.integration.dto.TorcedorCampanhaResponseDTO;
import br.com.tgolopes.repository.SocioTorcedorRepository;

@Service
@Validated
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SocioTorcedorService {
	
	@Autowired
	private SocioTorcedorRepository socioTorcedorRepository;
	
	@Autowired 
	private TimeCoracaoFacade timeCoracaoFacade;

	@Transactional
	public SocioTorcedor salvar(final SocioTorcedor socioTorcedor) throws EmailJaCadastrado{
		if (this.emailExistente(socioTorcedor.getEmail())) throw new EmailJaCadastrado();
		return this.socioTorcedorRepository.save(socioTorcedor);
	}

	@Transactional
	public void deletar(final SocioTorcedor socioTorcedor){
		this.socioTorcedorRepository.delete(socioTorcedor);
	}
	
	public SocioTorcedor pesquisarPorId(final Long id) throws SocioTorcedorNaoEncontradoException{
		if (this.socioTorcedorRepository.findOneById(id) == null)
			throw new SocioTorcedorNaoEncontradoException();
		return this.socioTorcedorRepository.findOneById(id);
	}
	
	public List<SocioTorcedor> pesquisarTodos(){
		return this.socioTorcedorRepository.findAll();
	}
	
	private boolean emailExistente(final String email){
		SocioTorcedor socio = this.socioTorcedorRepository.findByEmail(email);
		return socio != null ? true : false;
	}
	
	public CampanhaResponseDTO pesquisarCampanhaPorId(final Long idCampanha) {
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/v1/campanha/{idCampanha}";
	    CampanhaResponseDTO campanhaResponseDTO = restTemplate.getForObject(url, CampanhaResponseDTO.class, idCampanha);
	    return campanhaResponseDTO;
	}
	
	public ResponseEntity<CampanhaResponseDTO[]> pesquisarCampanhasVigentesPorTime(String nome) throws TimeCoracaoNaoEncontrado{
		TimeCoracaoResponse timeCoracaoResponse = this.timeCoracaoFacade.buscarTimePorNome(nome);
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/v1/campanha/time/{codigoTime}";
	    ResponseEntity<CampanhaResponseDTO[]> campanhaResponseDTO = restTemplate.getForEntity(url, CampanhaResponseDTO[].class, timeCoracaoResponse.getCodigoTime());
	    return campanhaResponseDTO;
	}
	
	public ResponseEntity<CampanhaResponseDTO[]> pesquisarCampanhasVigentes() {
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/v1/campanha";
	    ResponseEntity<CampanhaResponseDTO[]> campanhaResponseDTO = restTemplate.getForEntity(url, CampanhaResponseDTO[].class);
	    return campanhaResponseDTO;
	}
	
	public CampanhaResponseDTO salvarCampanha(CampanhaRequestDTO campanhaRequestDTO) {
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/v1/campanha";
	    CampanhaResponseDTO campanhaResponseDTO = restTemplate.postForObject(url, campanhaRequestDTO, CampanhaResponseDTO.class);
	    return campanhaResponseDTO;
	}
	
	public TorcedorCampanhaResponseDTO associarCampanha(TorcedorCampanhaRequestDTO torcedorCampanhaRequestDTO) throws SocioTorcedorNaoEncontradoException {
		if(!this.emailExistente(torcedorCampanhaRequestDTO.getEmail())) throw new SocioTorcedorNaoEncontradoException();
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/v1/associacoes";
	    TorcedorCampanhaResponseDTO torcedorCampanhaResponseDTO = restTemplate.postForObject(url, torcedorCampanhaRequestDTO, TorcedorCampanhaResponseDTO.class);
	    return torcedorCampanhaResponseDTO;
	}
}