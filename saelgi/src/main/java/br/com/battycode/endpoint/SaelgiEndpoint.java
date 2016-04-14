package br.com.battycode.endpoint;

import br.com.battycode.dao.LicitacaoDAO;
import br.com.battycode.dto.Licitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class SaelgiEndpoint {

	@Autowired
	private LicitacaoDAO licitacaoDAO;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Mapa licitatorio");
		return model;
	}
	
	@RequestMapping(value = "/licitacoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Licitacao>> licitacoes() {
		List<Licitacao> licitacoes = licitacaoDAO.findAll();
		return new ResponseEntity<>(licitacoes, HttpStatus.OK);
	}
}
