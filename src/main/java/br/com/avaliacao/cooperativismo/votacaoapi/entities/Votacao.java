package br.com.avaliacao.cooperativismo.votacaoapi.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_votacao")
public class Votacao {
	
	@EmbeddedId
	private VotacaoPK id = new VotacaoPK();
	private String voto;
	
	public Votacao() {}
	
	public void setPauta(Pauta pauta) {
		id.setPauta(pauta);
	}
	
	public void setAssociado(Associado associado) {
		id.setAssociado(associado);
	}

	public VotacaoPK getId() {
		return id;
	}

	public void setId(VotacaoPK id) {
		this.id = id;
	}

	public String getVoto() {
		return voto;
	}

	public void setVoto(String voto) {
		this.voto = voto;
	};

}
