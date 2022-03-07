package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.services.PautaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pautas")
public class PautaController {
	
	Logger logger = LoggerFactory.getLogger(PautaController.class);

	@Autowired
	private PautaService pautaService;	
	
	@GetMapping
	@ApiOperation(value = "Busca todas pautas de forma Paginada, onde pageNumber=\"numero pagina a ser exibida\" e pageSize=\"quantidade de paginas a serem paginadas\"", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<PautaDTO> buscarPautas(Pageable pageable){
		logger.info("Acessou a controller de busca de Pauta paginado");
		return pautaService.buscarTodos(pageable);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca a pauta pelo id ", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PautaDTO buscaPautaPorId(@PathVariable Long id) {
		logger.info("Acessou a controller de buscar de Pauta por id");
		return pautaService.buscarPautaPorCodigo(id);
	}
	
}
