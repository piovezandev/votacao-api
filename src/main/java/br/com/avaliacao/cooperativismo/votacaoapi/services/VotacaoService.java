package br.com.avaliacao.cooperativismo.votacaoapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.dto.StatusDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.dto.VotacaoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Votacao;
import br.com.avaliacao.cooperativismo.votacaoapi.enumeration.VotacaoEnum;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.AssociadoRepository;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.VotacaoRepository;

@Service
public class VotacaoService {

	private static final String VALIDA_CPF_API = "https://user-info.herokuapp.com/users/%s";

	@Autowired
	private VotacaoRepository votacaoRepository;

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PautaService pautaService;
	
	@Transactional
	public PautaDTO criaSessaoVotoPauta(VotacaoDTO votacaoDTO) {
		boolean isDeveVotar = validaCpf(votacaoDTO.getCpf());

		if (true) {

			Associado associado = associadoRepository.findByCpf(votacaoDTO.getCpf());

			if (associado == null) {
				associado = new Associado();
				associado.setNome(votacaoDTO.getNome());
				associado.setCpf(votacaoDTO.getCpf());
				associado = associadoRepository.saveAndFlush(associado);
			}
			
			Pauta pauta = new Pauta();
			pauta.setId(1L);
			
			pautaService.abrirSessao(pauta);
			
			Votacao votacao = new Votacao();
			votacao.setAssociado(associado);
			votacao.setVoto(votacaoDTO.getVoto());
			votacao.setPauta(pauta);
			
			votacao = votacaoRepository.saveAndFlush(votacao);	
			
			Integer teste  = getSomaValores(pauta.getId());
			
			pauta.setCount(teste);
			
			return new PautaDTO(pauta);
			
		}

		return null;
	}

	private Boolean validaCpf(String cpf) {
		RestTemplate restTemplate = new RestTemplate();

		StatusDTO status = restTemplate.getForObject(String.format(VALIDA_CPF_API, cpf), StatusDTO.class);

		if (status.getStatus().equals(VotacaoEnum.ABLE_TO_VOTE.isDeveVotar)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Integer getSomaValores(Long id) {
		List<Votacao> listaEntidade = votacaoRepository.findAll();	
//		&& p.getId().getPauta().getId()== id
		Long somaValores = listaEntidade
				.stream()
				.filter(p -> p.getVoto().equals("SIM") || p.getVoto().equals("sim") ).count();
		
		Integer resultado = somaValores.intValue();
		
		return resultado;
	}

}
