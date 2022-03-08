package br.com.avaliacao.cooperativismo.votacaoapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.dto.VotacaoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Votacao;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.AssociadoRepository;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.PautaRepository;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.VotacaoRepository;
import br.com.avaliacao.cooperativismo.votacaoapi.utils.UserAPI;

@Service
public class VotacaoService {

	Logger logger = LoggerFactory.getLogger(VotacaoService.class);

	@Autowired
	private VotacaoRepository votacaoRepository;

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private PautaService pautaService;

	@Autowired
	private UserAPI userAPI;

	@Transactional
	public ResponseEntity<PautaDTO> criaSessaoVotoPauta(VotacaoDTO votacaoDTO) {
		boolean isDeveVotar = userAPI.validaCpf(votacaoDTO.getCpf());

		logger.info(String.format("CPF valido? : %s", isDeveVotar));
		
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
			
			logger.info(String.format("Criando Pauta para votação, CPF associado: [%s]", associado.getCpf()));
			
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
				
				logger.info(String.format("Votação concluida, CPF associado: [%s], voto: [%s]", associado.getCpf(), votacao.getVoto()));

			} else {
				pauta.setDataSessao(LocalDateTime.now());

				pautaService.abrirSessao(pauta);

				Votacao votacao = new Votacao();
				votacao.setAssociado(associado);
				votacao.setVoto(votacaoDTO.getVoto());
				votacao.setPauta(pauta);

				votacao = votacaoRepository.saveAndFlush(votacao);
				
				logger.info(String.format("Votação concluida, CPF associado: [%s], voto: [%s]", associado.getCpf(), votacao.getVoto()));

			}

			Integer resultado = getSomaVotos(pauta.getId());

			pauta.setCount(resultado);

			pautaRepository.saveAndFlush(pauta);
			
			return new ResponseEntity<>(new PautaDTO(pauta), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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

		boolean isDuplicado = listaEntidade.stream()
				.anyMatch(p -> p.getId().getPauta().getId() == id && p.getId().getAssociado().getId() == idAssociado);

		return isDuplicado;
	}

}
