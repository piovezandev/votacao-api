package br.com.avaliacao.cooperativismo.votacaoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long>{
	@Query(value = "SELECT ID FROM TB_PAUTA ORDER BY ID DESC LIMIT 1", nativeQuery = true)
	Long findId();
}
