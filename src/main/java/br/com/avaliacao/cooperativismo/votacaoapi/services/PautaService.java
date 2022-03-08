package br.com.avaliacao.cooperativismo.votacaoapi.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.PautaRepository;

/**
 * Classe responsavel por conter as regras de negocio responsaveis pela gestão das <b>Pautas</b>
 * @author alan
 * @since 2022
 */
@Service
public class PautaService {

	Logger logger = LoggerFactory.getLogger(PautaService.class);

	@Autowired
	PautaRepository pautaRepository;
	
	public Pauta abrirSessao(Pauta pauta) {
		logger.info("Abrindo nova sessao de pauta");
		
		pautaRepository.saveAndFlush(pauta);
		return pauta;
	}
	
	public Page<PautaDTO> buscarTodos(Pageable pageable) {
		logger.info("Buscando pautas de forma Paginada");

		Page<Pauta> listaEntidade = pautaRepository.findAll(pageable);
		Page<PautaDTO> response = listaEntidade.map(n -> new PautaDTO(n));
		return response;
	}
	
	public PautaDTO buscarPautaPorCodigo(Long id) {
		logger.info(String.format("Buscando pauta por codigo id: [%s]", id));

		List<Pauta> listaEntidade = pautaRepository.findAll();		
		
		return listaEntidade
				.stream()
				.filter(p -> p.getId().equals(id))
				.map(p -> new PautaDTO(p)).findFirst().get();
	}
	
}
