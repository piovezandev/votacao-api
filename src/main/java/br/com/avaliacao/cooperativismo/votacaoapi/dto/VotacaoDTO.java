package br.com.avaliacao.cooperativismo.votacaoapi.dto;

public class VotacaoDTO {
	
	private String nome;
	private String cpf;
	private String voto;
	
	VotacaoDTO(){}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getVoto() {
		return voto;
	}

	public void setVoto(String voto) {
		this.voto = voto;
	}
	
}
