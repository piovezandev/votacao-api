package br.com.avaliacao.cooperativismo.votacaoapi.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

import br.com.avaliacao.cooperativismo.votacaoapi.dto.AssociadoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.entities.Associado;
import br.com.avaliacao.cooperativismo.votacaoapi.repositories.AssociadoRepository;


@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTest {
		
	@InjectMocks
	private AssociadoService associadoService;
	
	@Mock
	private AssociadoRepository repository;
	
	@Test
	public void deve_salvar_associado() {				
		when(repository.save(Mockito.any(Associado.class))).thenReturn(getMockAssociado());
		
		AssociadoDTO associadoDTO = associadoService.salvarAssociado(getMockAssociado());
		assertNotNull(associadoDTO);
	}
	
	
	@Test
	public void deve_buscar_associado() {	
        Pageable pageable = PageRequest.of(0, 8);
        
        List<Associado> listaEntidade = Arrays.asList(getMockAssociado());
        
        Page<Associado> page = new PageImpl<Associado>(listaEntidade);
        
        when(repository.findAll(pageable)).thenReturn(page);
        
		Page<AssociadoDTO> associadoDTO = associadoService.buscarTodos(pageable);
		assertNotNull(associadoDTO);	
	}
	
	@Test
	public void deve_buscar_associado_por_cpf() {	
 
        List<Associado> listaEntidade = Arrays.asList(getMockAssociado());
        
        when(repository.findAll()).thenReturn(listaEntidade);
        
        AssociadoDTO associadoDTO = associadoService.buscarAssociadoPorCPF("11111111111");
		assertEquals(associadoDTO.getNome(), "Test");	
	}
	
	
	private Associado getMockAssociado() {
		Associado associado = new Associado();
		associado.setId(1L);
		associado.setCpf("11111111111");
		associado.setNome("Test");
		
		return associado;		
	}
}
