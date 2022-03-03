package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

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

	@Autowired
	private PautaService pautaService;
	
	@PostMapping(value = "/criar")
	public Pauta cadastraPauta(@RequestBody Pauta pauta) {
		return pautaService.abrirSessao(pauta);
	}
	
	@GetMapping
	public Page<PautaDTO> buscarPautas(Pageable pageable){
		return pautaService.buscarTodos(pageable);
	}
	
	@GetMapping("/{id}")
	public PautaDTO buscaPautaPorId(@PathVariable Long id) {
		return pautaService.buscarPautaPorCodigo(id);
	}
	
}
