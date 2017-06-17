package br.com.tgolopes.controller.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.tgolopes.controller.request.SocioTorcedorRequest;
import br.com.tgolopes.controller.response.SocioTorcedorResponse;
import br.com.tgolopes.entity.SocioTorcedor;
import br.com.tgolopes.exception.EmailJaCadastrado;
import br.com.tgolopes.exception.SocioTorcedorNaoEncontradoException;
import br.com.tgolopes.exception.TimeCoracaoNaoEncontrado;
import br.com.tgolopes.integration.dto.CampanhaRequestDTO;
import br.com.tgolopes.integration.dto.CampanhaResponseDTO;
import br.com.tgolopes.integration.dto.TorcedorCampanhaRequestDTO;
import br.com.tgolopes.integration.dto.TorcedorCampanhaResponseDTO;
import br.com.tgolopes.repository.SocioTorcedorRepository;
import br.com.tgolopes.service.SocioTorcedorService;

@Component
public class SocioTorcedorFacade {
	@Autowired
	private SocioTorcedorService socioTorcedorService;
	
	@Autowired
	private SocioTorcedorRepository socioTorcedorRepository;
	
	
	public SocioTorcedorResponse salvar(SocioTorcedorRequest socioTorcedorRequest) throws EmailJaCadastrado {
		return new SocioTorcedorResponse (this.socioTorcedorService.salvar(socioTorcedorRequest.toSocioTorcedor()));
	}
	
	public SocioTorcedorResponse alterar(Long id, SocioTorcedorRequest socioTorcedorRequest) throws SocioTorcedorNaoEncontradoException, EmailJaCadastrado { 
		Optional.ofNullable(socioTorcedorService.pesquisarPorId(id)).orElseThrow(() -> new SocioTorcedorNaoEncontradoException());
		SocioTorcedor socioTorcedor = socioTorcedorRequest.toSocioTorcedor();
		socioTorcedor.setId(id);
		return new SocioTorcedorResponse (this.socioTorcedorService.salvar(socioTorcedor));
	}
	
	public void excluir(Long id) throws SocioTorcedorNaoEncontradoException  {
		SocioTorcedor socioTorcedor = Optional.ofNullable(socioTorcedorService.pesquisarPorId(id)).orElseThrow(() -> new SocioTorcedorNaoEncontradoException());
		this.socioTorcedorService.deletar(socioTorcedor);
	}
	
	public SocioTorcedorResponse buscar(Long id) throws SocioTorcedorNaoEncontradoException {
		return Optional.ofNullable(socioTorcedorService.pesquisarPorId(id)).map(SocioTorcedorResponse::new).orElseThrow(() -> new SocioTorcedorNaoEncontradoException());
	}
	
	public List<SocioTorcedorResponse> buscarTodos() {
		return socioTorcedorService.pesquisarTodos().stream().map(SocioTorcedorResponse::new).collect(Collectors.toList());
	}
	
	public CampanhaResponseDTO buscarCampanhaPorId(Long idCampanha) {
		return socioTorcedorService.pesquisarCampanhaPorId(idCampanha);
	}
	
	public ResponseEntity<CampanhaResponseDTO[]> buscarCampanhasVigentesPorTime(String nome) throws TimeCoracaoNaoEncontrado {
		return socioTorcedorService.pesquisarCampanhasVigentesPorTime(nome);
	}
	
	public ResponseEntity<CampanhaResponseDTO[]> buscarCampanhasVigentes() {
		return socioTorcedorService.pesquisarCampanhasVigentes();
	}
	
	public CampanhaResponseDTO salvarCampanha(CampanhaRequestDTO campanhaRequestDTO) {
		return this.socioTorcedorService.salvarCampanha(campanhaRequestDTO);
	}
	
	public TorcedorCampanhaResponseDTO associarCampanha(TorcedorCampanhaRequestDTO torcedorCampanhaRequestDTO) throws SocioTorcedorNaoEncontradoException {
		return this.socioTorcedorService.associarCampanha(torcedorCampanhaRequestDTO);
	}
}