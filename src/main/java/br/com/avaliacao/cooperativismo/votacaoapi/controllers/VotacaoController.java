package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private VotacaoService votacaoService;
	
	@PutMapping
	public PautaDTO votar(@RequestBody VotacaoDTO votacaoDTO){
		PautaDTO pautaDTO = votacaoService.criaSessaoVotoPauta(votacaoDTO);
		return pautaDTO;
	}
	
	

}
