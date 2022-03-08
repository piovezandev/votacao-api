package br.com.avaliacao.cooperativismo.votacaoapi.enumeration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VotacaoEnumTest {

	@InjectMocks
	private VotacaoEnum votacaoEnum;
	
	@Test
	public void deve_validar_enum_correto() {		
		assertEquals("ABLE_TO_VOTE", VotacaoEnum.ABLE_TO_VOTE.isDeveVotar);
		assertEquals("UNABLE_TO_VOTE", VotacaoEnum.UNABLE_TO_VOTE.isDeveVotar);
	}

}
