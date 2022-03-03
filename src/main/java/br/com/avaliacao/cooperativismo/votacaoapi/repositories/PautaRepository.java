package br.com.avaliacao.cooperativismo.votacaoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long>{

}
