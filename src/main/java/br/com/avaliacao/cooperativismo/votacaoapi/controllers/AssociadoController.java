package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.AssociadoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;
import br.com.avaliacao.cooperativismo.votacaoapi.services.AssociadoService;

@RestController
@RequestMapping(value = "/associado")
public class AssociadoController {
	
	 Logger logger = LoggerFactory.getLogger(AssociadoController.class);
	
	@Autowired
	private AssociadoService associadoService;

	@PostMapping(value = "/cadastro")
	public AssociadoDTO cadastrarAssociado(@RequestBody Associado associado){
		return associadoService.salvarAssociado(associado);
	}
	
	@GetMapping
	public Page<AssociadoDTO> buscarTodosAssociados(Pageable pageable){
		return associadoService.buscarTodos(pageable);
	}
	
	@GetMapping("/{cpf}")
	public AssociadoDTO buscaAssociadoPorCpf(@PathVariable String cpf) {
		return associadoService.buscarAssociadoPorCPF(cpf);
	}
	
}
