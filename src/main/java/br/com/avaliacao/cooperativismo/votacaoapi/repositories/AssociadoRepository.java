package br.com.avaliacao.cooperativismo.votacaoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long>{
	Associado findByCpf(String cpf);
}
