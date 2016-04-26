package br.com.battycode.endpoint;

import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Modalidade;
import br.com.battycode.dto.Orgao;
import br.com.battycode.service.LicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class SaelgiEndpoint {

	@Autowired
	private LicitacaoService licitacaoService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Mapa licitatorio");
		return model;
	}

	@RequestMapping(value = "/licitacoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Licitacao>> getLicitacoes() {
		List<Licitacao> licitacoes = licitacaoService.obterTodasLicitacoes();
		return new ResponseEntity<>(licitacoes, HttpStatus.OK);
	}

	@RequestMapping(value = "/licitacoes/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Licitacao> getLicitacao(@PathVariable("codigo") Integer codigo) {
		Licitacao licitacao = licitacaoService.obterLicitacao(codigo);
		return new ResponseEntity<>(licitacao, HttpStatus.OK);
	}

	@RequestMapping(value = "/licitacoes/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteLicitacao(@PathVariable("codigo") Integer codigo) {
		licitacaoService.removerLicitacao(codigo);
	}

	@RequestMapping(value = "/criarEditarLicitacao", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void criarEditarLicitacao(@RequestBody Licitacao licitacao) {
		licitacaoService.criarEditarLicitacao(licitacao);
	}

	@RequestMapping(value = "/modalidades", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Modalidade>> getModalidades() {
		List<Modalidade> modalidades = licitacaoService.obterTodasModalidades();
		return new ResponseEntity<>(modalidades, HttpStatus.OK);
	}

    @RequestMapping(value = "/orgaos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Orgao>> getOrgaos() {
        List<Orgao> orgaos = licitacaoService.obterOrgaos();
        return new ResponseEntity<>(orgaos, HttpStatus.OK);
    }
}
