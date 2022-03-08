package br.com.avaliacao.cooperativismo.votacaoapi.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Pauta;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.PautaRepository;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {
	
	@InjectMocks
	PautaService pautaService;
	
	@Mock
	PautaRepository repository;
	
	@Test
	public void deve_abrir_sessao() {
		when(repository.saveAndFlush(Mockito.any(Pauta.class))).thenReturn(getMockPauta());
		
		Pauta pauta = pautaService.abrirSessao(getMockPauta());
		assertNotNull(pauta);
	}
	
	@Test
	public void deve_buscar_pauta_paginada() {	
        Pageable pageable = PageRequest.of(0, 8);
        
        List<Pauta> listaEntidade = Arrays.asList(getMockPauta());
        
        Page<Pauta> page = new PageImpl<Pauta>(listaEntidade);
        
        when(repository.findAll(pageable)).thenReturn(page);
        
		Page<PautaDTO> pautaDTO = pautaService.buscarTodos(pageable);
		assertNotNull(pautaDTO);	
	}

	
	@Test
	public void deve_buscar_pauta_por_id() {	
 
        List<Pauta> listaEntidade = Arrays.asList(getMockPauta());
        
        when(repository.findAll()).thenReturn(listaEntidade);
        
        PautaDTO pautaDTO = pautaService.buscarPautaPorCodigo(1L);
		assertNotNull(pautaDTO);			
	}
	
	
	private Pauta getMockPauta() {
		Pauta pauta = new Pauta();
		pauta.setId(1L);
		pauta.setCount(10);
		pauta.setDataSessao(LocalDateTime.now());

		return pauta;
	}
}
