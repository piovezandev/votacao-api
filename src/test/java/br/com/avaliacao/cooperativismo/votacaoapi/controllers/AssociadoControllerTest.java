package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.AssociadoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.services.AssociadoService;

@RunWith(SpringRunner.class)
@WebMvcTest(AssociadoController.class)
public class AssociadoControllerTest {

    @MockBean
    private AssociadoService associadoService;	
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
	@Test
	public void deve_cadastrar_associado_sucesso() throws Exception {			
		MvcResult mvcResult = mockMvc.perform(post("/associado/cadastro")
		.contentType("application/json")
        .content(objectMapper.writeValueAsString(getMockAssociado())))
        .andExpect(status().isOk())
        .andReturn();
		
		 assertNotNull(mvcResult.getResponse());		
	}
	
	@Test
	public void deve_buscar_associados_sucesso() throws Exception {			
		MvcResult mvcResult = mockMvc.perform(get("/associado"))
        .andExpect(status().isOk())
        .andReturn();
		
		 assertNotNull(mvcResult.getResponse());		
	}
	
	@Test
	public void deve_buscar_associado_CPF_sucesso() throws Exception {		
		 MvcResult mvcResult = mockMvc.perform(get("/associado/{cpf}","11111111111"))
				 .andExpect(status().isOk())
				 .andReturn();
		 
		 assertNotNull(mvcResult.getResponse().getContentAsString());		
	}
	
	private AssociadoDTO getMockAssociado() {
		AssociadoDTO associadoDTO = new AssociadoDTO();
		associadoDTO.setId(1L);
		associadoDTO.setCpf("11111111111");
		associadoDTO.setNome("Test");
		return associadoDTO;
	}
	

}
