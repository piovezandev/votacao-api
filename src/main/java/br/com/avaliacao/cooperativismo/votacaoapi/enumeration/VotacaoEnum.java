package br.com.avaliacao.cooperativismo.votacaoapi.enumeration;

public enum VotacaoEnum {

	UNABLE_TO_VOTE("UNABLE_TO_VOTE"),
	ABLE_TO_VOTE("ABLE_TO_VOTE");

	public String isDeveVotar;
	
	VotacaoEnum(String retornoAPI) {
		isDeveVotar = retornoAPI;
	}
	
}
