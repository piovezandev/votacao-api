package br.com.avaliacao.cooperativismo.votacaoapi.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pauta")
public class Pauta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer count;
	@OneToMany(mappedBy = "id.pauta")
	private Set<Votacao> votos = new HashSet<>();
	@Column(name = "data_sessao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataSessao;

	public Pauta() {}

	public Pauta(Long id, Integer count) {
		this.id = id;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set<Votacao> getVotos() {
		return votos;
	}

	public LocalDateTime getDataSessao() {
		return dataSessao;
	}

	public void setDataSessao(LocalDateTime dataSessao) {
		this.dataSessao = dataSessao;
	};	

}
