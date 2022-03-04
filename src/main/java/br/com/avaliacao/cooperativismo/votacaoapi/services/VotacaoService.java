package br.com.avaliacao.cooperativismo.votacaoapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.PautaRepository;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.VotacaoRepository;

@Service
public class VotacaoService {

	private static final String VALIDA_CPF_API = "https://user-info.herokuapp.com/users/%s";

	@Autowired
	private VotacaoRepository votacaoRepository;

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private PautaService pautaService;

	@Transactional
	public ResponseEntity<PautaDTO> criaSessaoVotoPauta(VotacaoDTO votacaoDTO) {
		boolean isDeveVotar = validaCpf(votacaoDTO.getCpf());

		if (isDeveVotar) {

			Associado associado = associadoRepository.findByCpf(votacaoDTO.getCpf());

			if (associado == null) {
				associado = new Associado();
				associado.setNome(votacaoDTO.getNome());
				associado.setCpf(votacaoDTO.getCpf());
				associado = associadoRepository.saveAndFlush(associado);
			}

			if (getVotoAssociadoDuplicado(getIdSession(), associado.getId()) && validaData()) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			Pauta pauta = new Pauta();

			if (validaData()) {

				pauta.setDataSessao(LocalDateTime.now());
				pauta.setId(getIdSession());

				pautaService.abrirSessao(pauta);

				Votacao votacao = new Votacao();
				votacao.setAssociado(associado);
				votacao.setVoto(votacaoDTO.getVoto());
				votacao.setPauta(pauta);

				votacao = votacaoRepository.saveAndFlush(votacao);

			} else {
				pauta.setDataSessao(LocalDateTime.now());

				pautaService.abrirSessao(pauta);

				Votacao votacao = new Votacao();
				votacao.setAssociado(associado);
				votacao.setVoto(votacaoDTO.getVoto());
				votacao.setPauta(pauta);

				votacao = votacaoRepository.saveAndFlush(votacao);
			}

			Integer resultado = getSomaVotos(pauta.getId());

			pauta.setCount(resultado);

			pautaRepository.saveAndFlush(pauta);
			
			return new ResponseEntity<>(new PautaDTO(pauta), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	private Boolean validaCpf(String cpf) {
		RestTemplate restTemplate = new RestTemplate();

		StatusDTO status = restTemplate.getForObject(String.format(VALIDA_CPF_API, cpf.trim()), StatusDTO.class);

		if (status.getStatus().equals(VotacaoEnum.ABLE_TO_VOTE.isDeveVotar)) {
			return true;
		} else {
			return false;
		}
	}

	private Integer getSomaVotos(Long id) {
		List<Votacao> listaEntidade = votacaoRepository.findAll();

		Long somaValores = listaEntidade.stream()
				.filter(p -> p.getVoto().equals("SIM") || p.getVoto().equals("sim") && p.getId().getPauta().getId() == id)
				.count();

		Integer resultado = somaValores.intValue();

		return resultado;
	}

	private Boolean validaData() {
		LocalDateTime dataHoraAtual = LocalDateTime.now();

		List<Pauta> listaEntidade = pautaRepository.findAll();

		boolean retorno = listaEntidade.stream()
				.anyMatch(p -> dataHoraAtual.isBefore(p.getDataSessao().plusMinutes(1)));

		return retorno;
	}

	private Long getIdSession() {
		Long idSessao = pautaRepository.findId();

		if (idSessao == null) {
			return 1L;
		}

		return idSessao;
	}

	private  Boolean getVotoAssociadoDuplicado(Long id, Long idAssociado) {
		List<Votacao> listaEntidade = votacaoRepository.findAll();

		boolean teste = listaEntidade.stream()
				.anyMatch(p -> p.getId().getPauta().getId() == id && p.getId().getAssociado().getId() == idAssociado);

		return teste;
	}

}
