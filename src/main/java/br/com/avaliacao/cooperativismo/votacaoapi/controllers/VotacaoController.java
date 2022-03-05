package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.dto.VotacaoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.services.VotacaoService;

@RestController
@RequestMapping(value = "/votacao")
public class VotacaoController {
	
	Logger logger = LoggerFactory.getLogger(VotacaoController.class);
	
	@Autowired
	private VotacaoService votacaoService;
	
	@PutMapping
	public ResponseEntity<PautaDTO> votar(@RequestBody VotacaoDTO votacaoDTO){
		logger.info("Acessando a controller de votação");
		return votacaoService.criaSessaoVotoPauta(votacaoDTO);
	}
	
	

}
