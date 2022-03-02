package br.com.avaliacao.cooperativismo.votacaoapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.AssociadoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.AssociadoRepository;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository repository;

	public AssociadoDTO salvarAssociado(Associado associado){
		repository.save(associado);
		return new AssociadoDTO(associado);		
	}
	
	public Page<AssociadoDTO> buscarTodos(Pageable pageable) {
		Page<Associado> listaEntidade = repository.findAll(pageable);
		Page<AssociadoDTO> response = listaEntidade.map(n -> new AssociadoDTO(n));
		return response;
	}
	
	public AssociadoDTO buscarAssociadoPorCPF(Pageable pageable, String cpf) {
		List<Associado> listaEntidade = repository.findAll();		
		
		return listaEntidade
				.stream()
				.filter(p -> p.getCpf().equals(cpf))
				.map(p -> new AssociadoDTO(p)).findFirst().get();
	}
}
