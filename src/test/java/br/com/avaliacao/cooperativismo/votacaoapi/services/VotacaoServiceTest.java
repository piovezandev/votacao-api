package br.com.avaliacao.cooperativismo.votacaoapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.dto.VotacaoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Votacao;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.AssociadoRepository;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.PautaRepository;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.VotacaoRepository;
import br.com.avaliacao.cooperativismo.votacaoapi.utils.UserAPI;

@ExtendWith(MockitoExtension.class)
public class VotacaoServiceTest {
	

	@InjectMocks
	private VotacaoService votacaoService;
	
	@Mock
	private VotacaoRepository votacaoRepository;

	@Mock
	private AssociadoRepository associadoRepository;

	@Mock
	private PautaRepository pautaRepository;

	@Mock
	private PautaService pautaService;
	
	@Mock
	UserAPI userAPI;
	
	@Test
	public void deve_retornar_cpf_invalido() {
		when(userAPI.validaCpf(anyString())).thenReturn(false);
		
		ResponseEntity<PautaDTO> response =	votacaoService.criaSessaoVotoPauta(getMockVotacaoDTO());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void deve_criar_sessao_voto() {
		when(userAPI.validaCpf(anyString())).thenReturn(true);
		when(associadoRepository.findByCpf(anyString())).thenReturn(getMockAssociado());
		when(pautaService.abrirSessao(any())).thenReturn(getMockPauta());
		when(votacaoRepository.saveAndFlush(any())).thenReturn(getMockVotacao());

		ResponseEntity<PautaDTO> response =	votacaoService.criaSessaoVotoPauta(getMockVotacaoDTO());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	private VotacaoDTO getMockVotacaoDTO() {
		VotacaoDTO votacaoDTO = new VotacaoDTO();
		votacaoDTO.setNome("Test");
		votacaoDTO.setCpf("11111111111");
		votacaoDTO.setVoto("SIM");
		
		return votacaoDTO;
	}
	
	private Associado getMockAssociado() {
		Associado associado = new Associado();
		associado.setId(1L);
		associado.setCpf("11111111111");
		associado.setNome("Test");
		
		return associado;		
	}
	
	private Pauta getMockPauta() {
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		pauta.setCount(10);
		pauta.setDataSessao(LocalDateTime.now());

		return pauta;
	}
	
	private Votacao getMockVotacao() {
		Votacao votacao = new Votacao();
		votacao.setAssociado(getMockAssociado());
		votacao.setPauta(getMockPauta());
		votacao.setVoto("SIM");
		
		return votacao;
	}
}
