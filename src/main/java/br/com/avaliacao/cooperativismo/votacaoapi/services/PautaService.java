package br.com.avaliacao.cooperativismo.votacaoapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.PautaRepository;

@Service
public class PautaService {

	@Autowired
	PautaRepository pautaRepository;
	
	public Pauta abrirSessao(Pauta pauta) {
		pautaRepository.saveAndFlush(pauta);
		return pauta;
	}
	
	public Page<PautaDTO> buscarTodos(Pageable pageable) {
		Page<Pauta> listaEntidade = pautaRepository.findAll(pageable);
		Page<PautaDTO> response = listaEntidade.map(n -> new PautaDTO(n));
		return response;
	}
	
	public PautaDTO buscarPautaPorCodigo(Long id) {
		List<Pauta> listaEntidade = pautaRepository.findAll();		
		
		return listaEntidade
				.stream()
				.filter(p -> p.getId().equals(id))
				.map(p -> new PautaDTO(p)).findFirst().get();
	}
	
}
