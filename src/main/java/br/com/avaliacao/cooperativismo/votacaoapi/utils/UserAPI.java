package br.com.avaliacao.cooperativismo.votacaoapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.StatusDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.enumeration.VotacaoEnum;

@Service
public class UserAPI {
	
	private static final String VALIDA_CPF_API = "https://user-info.herokuapp.com/users/%s";
	Logger logger = LoggerFactory.getLogger(UserAPI.class);
	
	public Boolean validaCpf(String cpf) {
		RestTemplate restTemplate = new RestTemplate();

		StatusDTO status = restTemplate.getForObject(String.format(VALIDA_CPF_API, cpf.trim()), StatusDTO.class);

		logger.info(String.format("Validando CPF, response API : %s", status.getStatus()));
		
		if (status.getStatus().equals(VotacaoEnum.ABLE_TO_VOTE.isDeveVotar)) {
			return true;
		} else {
			return false;
		}
	}

}
