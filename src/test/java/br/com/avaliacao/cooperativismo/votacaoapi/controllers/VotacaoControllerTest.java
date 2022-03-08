package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import br.com.avaliacao.cooperativismo.votacaoapi.dto.VotacaoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.services.VotacaoService;

@RunWith(SpringRunner.class)
@WebMvcTest(VotacaoController.class)
public class VotacaoControllerTest {

    @MockBean
    private VotacaoService votacaoService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void deve_votar_sucesso() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/votacao")
		.contentType("application/json")
        .content(objectMapper.writeValueAsString(getMockVoto())))
        .andExpect(status().isOk())
        .andReturn();

		assertNotNull(mvcResult.getResponse());
    }

    private VotacaoDTO getMockVoto() {
    	VotacaoDTO dto = new VotacaoDTO();
    	dto.setNome("Test");
    	dto.setCpf("11111111111");
    	dto.setVoto("SIM");
    	
    	return dto;
    }

}
