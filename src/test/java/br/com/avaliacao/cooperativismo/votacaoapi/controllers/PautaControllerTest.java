package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.avaliacao.cooperativismo.votacaoapi.services.PautaService;

@RunWith(SpringRunner.class)
@WebMvcTest(PautaController.class)
public class PautaControllerTest {

    @MockBean
    private PautaService pautaService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void deve_buscar_todas_pautas() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/pautas"))
				.andExpect(status().isOk())
				.andReturn();

		assertNotNull(mvcResult.getResponse());
    }

    @Test
    public void deve_buscar_pauta_por_id() throws Exception {
		 MvcResult mvcResult = mockMvc.perform(get("/pautas/{id}", 1L))
				 .andExpect(status().isOk())
				 .andReturn();
		 
		 assertNotNull(mvcResult.getResponse().getContentAsString());	
    }

}
