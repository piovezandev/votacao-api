package br.com.avaliacao.cooperativismo.votacaoapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.cooperativismo.votacaoapi.dto.PautaDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.dto.VotacaoDTO;
import br.com.avaliacao.cooperativismo.votacaoapi.services.VotacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/votacao")
public class VotacaoController {
	
	Logger logger = LoggerFactory.getLogger(VotacaoController.class);
	
	@Autowired
	private VotacaoService votacaoService;
		
	@PutMapping
	@ApiOperation(value = "Cria uma nova Pauta e popula o voto, ou somente popula o voto se pauta ja foi criada. Voto SIM ou NAO", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "CPF não encontrado na API user-info"),
	 @ApiResponse(code = 400, message = "CPF Associado ja votou nessa Pauta!, aguarde 1 minuto para nova pauta") })
	public ResponseEntity<PautaDTO> votar(@RequestBody VotacaoDTO votacaoDTO){
		logger.info("Acessando a controller de votação");
		return votacaoService.criaSessaoVotoPauta(votacaoDTO);
	}

}
