package br.com.avaliacao.cooperativismo.votacaoapi.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.AssociadoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.AssociadoRepository;

@Service
public class AssociadoService {
	
	Logger logger = LoggerFactory.getLogger(AssociadoService.class);
	
	@Autowired
	private AssociadoRepository repository;

	public AssociadoDTO salvarAssociado(Associado associado){
		logger.info(String.format("Cadastrando associado nome: [%s] CPF:[%s]", associado.getNome(),associado.getCpf()));
		repository.save(associado);
		return new AssociadoDTO(associado);		
	}
	
	public Page<AssociadoDTO> buscarTodos(Pageable pageable) {
		logger.info("Buscando todos associados");
		Page<Associado> listaEntidade = repository.findAll(pageable);
		Page<AssociadoDTO> response = listaEntidade.map(n -> new AssociadoDTO(n));
		return response;
	}
	
	public AssociadoDTO buscarAssociadoPorCPF(String cpf) {
		logger.info(String.format("Buscando associado por CPF associado:[%s]", cpf));
		
		List<Associado> listaEntidade = repository.findAll();		
		
		return listaEntidade
				.stream()
				.filter(p -> p.getCpf().equals(cpf))
				.map(p -> new AssociadoDTO(p)).findFirst().get();
	}
}
