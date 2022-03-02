package br.com.avaliacao.cooperativismo.votacaoapi.dto;

import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;

public class AssociadoDTO {

	private Long id;
	private String nome;
	private String cpf;
	
	public AssociadoDTO() {};
	
	public AssociadoDTO(Long id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public AssociadoDTO(Associado associado) {
		id = associado.getId();
		nome = associado.getNome();
		cpf = associado.getCpf();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
}
