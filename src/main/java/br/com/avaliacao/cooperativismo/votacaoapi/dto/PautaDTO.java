package br.com.avaliacao.cooperativismo.votacaoapi.dto;

import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;

public class PautaDTO {
	
	private Long id;
	private Integer resultado;
	
	public PautaDTO() {}

	public PautaDTO(Long id, Integer resultado) {
		this.id = id;
		this.resultado = resultado;
	}

	public PautaDTO(Pauta pauta) {
		id = pauta.getId();
		resultado = pauta.getCount();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	};

}
