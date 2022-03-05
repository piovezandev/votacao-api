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

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;
import br.com.avaliacao.cooperativismo.votacaoapi.services.PautaService;

@RestController
@RequestMapping(value = "/pautas")
public class PautaController {
	
	Logger logger = LoggerFactory.getLogger(PautaController.class);

	@Autowired
	private PautaService pautaService;
	
	@PostMapping(value = "/criar")
	public Pauta cadastraPauta(@RequestBody Pauta pauta) {
		logger.info("Acessou a controller de criação de Pauta");
		return pautaService.abrirSessao(pauta);
	}
	
	@GetMapping
	public Page<PautaDTO> buscarPautas(Pageable pageable){
		logger.info("Acessou a controller de busca de Pauta paginado");
		return pautaService.buscarTodos(pageable);
	}
	
	@GetMapping("/{id}")
	public PautaDTO buscaPautaPorId(@PathVariable Long id) {
		logger.info("Acessou a controller de buscar de Pauta por id");
		return pautaService.buscarPautaPorCodigo(id);
	}
	
}
