package br.com.avaliacao.cooperativismo.votacaoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.cooperativismo.votacaoapi.entities.Votacao;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.VotacaoPK;

public interface VotacaoRepository extends JpaRepository<Votacao, VotacaoPK>{

}
