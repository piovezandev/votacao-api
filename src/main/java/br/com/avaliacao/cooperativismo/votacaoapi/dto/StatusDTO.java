package br.com.avaliacao.cooperativismo.votacaoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusDTO {

	@JsonProperty
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
