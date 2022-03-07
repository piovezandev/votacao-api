package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.AssociadoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;
import br.com.avaliacao.cooperativismo.votacaoapi.services.AssociadoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/associado")
public class AssociadoController {
	
	Logger logger = LoggerFactory.getLogger(AssociadoController.class);
	
	@Autowired
	private AssociadoService associadoService;

	@PostMapping(value = "/cadastro")
	@ApiOperation(value = "Cadastra um novo associado ", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AssociadoDTO cadastrarAssociado(@RequestBody Associado associado){
		logger.info("Acessou controller cadastro de Associado");
		return associadoService.salvarAssociado(associado);
	}
	
	@GetMapping
	@ApiOperation(value = "Busca todos associados de forma Paginada, onde pageNumber=\\\"numero pagina a ser exibida\\\" e pageSize=\\\"quantidade de paginas a serem paginadas\\\"", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<AssociadoDTO> buscarTodosAssociados(Pageable pageable){
		logger.info("Acessou controller de buscar todos associados paginado");
		return associadoService.buscarTodos(pageable);
	}
	
	@GetMapping("/{cpf}")
	@ApiOperation(value = "Busca um associado pelo CPF ", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AssociadoDTO buscaAssociadoPorCpf(@PathVariable String cpf) {
		logger.info("Acessou controller de buscar  associado por CPF");
		return associadoService.buscarAssociadoPorCPF(cpf);
	}
	
}
