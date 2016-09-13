package br.com.battycode.endpoint;

import br.com.battycode.dto.*;
import br.com.battycode.service.LicitacaoService;
import br.com.battycode.service.OrgaoService;
import br.com.battycode.service.RepresentanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@RestController
public class SaelgiEndpoint {

	@Autowired
	private LicitacaoService licitacaoService;

	@Autowired
	private OrgaoService orgaoService;

    @Autowired
    private RepresentanteService representanteService;

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

	//Licitacoes

	@RequestMapping(value = "/licitacoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Licitacao>> getLicitacoes() {
		List<Licitacao> licitacoes = licitacaoService.obterTodasLicitacoes();
		return new ResponseEntity<>(licitacoes, HttpStatus.OK);
	}

	@RequestMapping(value = "/licitacao/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Licitacao> getLicitacao(@PathVariable("codigo") Integer codigo) {
		Licitacao licitacao = licitacaoService.obterLicitacao(codigo);
        licitacao.setNmArquivoEdital(licitacaoService.obterNmArquivoEdital(codigo));
		return new ResponseEntity<>(licitacao, HttpStatus.OK);
	}

	@RequestMapping(value = "/licitacao/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteLicitacao(@PathVariable("codigo") Integer codigo) {
		licitacaoService.removerLicitacao(codigo);
	}

	@RequestMapping(value = "/criarEditarLicitacao", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void criarEditarLicitacao(@RequestBody Licitacao licitacao) throws IOException{
		licitacaoService.criarEditarLicitacao(licitacao);
	}

    @RequestMapping(value = "/licitacao/edital/{codigo}", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> obterEditalLicitacao(@PathVariable("codigo") Integer codigo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        Edital edital = licitacaoService.obterEdital(codigo);
        byte[] contents = edital.getBlEdital();
        String filename = edital.getNmArquivoEdital();
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }

	@RequestMapping(value = "/licitacao/edital/upload/{codigo}", method = RequestMethod.POST)
	public void uploadArquivoLicitacao(MultipartHttpServletRequest request, @PathVariable("codigo") Integer codigo){
		try {
            Iterator<String> itr = request.getFileNames();
            MultipartFile file = request.getFile(itr.next());

            Edital edital = new Edital();
            edital.setCodigoLicitacao(codigo);
            edital.setNmArquivoEdital(file.getOriginalFilename());
            edital.setBlEdital(file.getBytes());

            licitacaoService.criarEditarEdital(edital);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/modalidades", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Modalidade>> getModalidades() {
		List<Modalidade> modalidades = licitacaoService.obterTodasModalidades();
		return new ResponseEntity<>(modalidades, HttpStatus.OK);
	}


	//Orgaos

	@RequestMapping(value = "/orgaos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orgao>> getOrgaos() {
		List<Orgao> orgaos = orgaoService.obterTodosOrgaos();
		return new ResponseEntity<>(orgaos, HttpStatus.OK);
	}

	@RequestMapping(value = "/orgao/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orgao> getOrgao(@PathVariable("codigo") Integer codigo) {
		Orgao orgao = orgaoService.obterOrgao(codigo);
		return new ResponseEntity<>(orgao, HttpStatus.OK);
	}

	@RequestMapping(value = "/orgao/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteOrgao(@PathVariable("codigo") Integer codigo) {
		orgaoService.removerOrgao(codigo);
	}

	@RequestMapping(value = "/criarEditarOrgao", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void criarEditarOrgao(@RequestBody Orgao orgao) {
		orgaoService.criarEditarOrgao(orgao);
	}

	@RequestMapping(value = "/esferas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Esfera>> getEsferas() {
		List<Esfera> esferas = new ArrayList<>();
		esferas.add(Esfera.Estadual);
		esferas.add(Esfera.Federal);
		esferas.add(Esfera.Municipal);
		return new ResponseEntity<>(esferas, HttpStatus.OK);
	}

	//Endereco

	@RequestMapping(value = "/editarEndereco", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void editarEndereco(@RequestBody Endereco endereco) {
		orgaoService.editarEndereco(endereco);
	}

	@RequestMapping(value = "/endereco/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Endereco> getEndereco(@PathVariable("codigo") Integer codigo) {
		Endereco endereco = orgaoService.obterEndereco(codigo);
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}

	@RequestMapping(value = "/obterListaUF", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String[]> getListaUF() {
		String[] ufList = orgaoService.obterListaUF();
		return new ResponseEntity<>(ufList, HttpStatus.OK);
	}

	//Representante

    @RequestMapping(value = "/representantes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Representante>> getRepresentantes() {
        List<Representante> representantes = representanteService.obterTodosRepresentante();
        return new ResponseEntity<>(representantes, HttpStatus.OK);
    }

    @RequestMapping(value = "/representante/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Representante> getRepresentante(@PathVariable("codigo") Integer codigo) {
        Representante representante = representanteService.obterRepresentante(codigo);
        return new ResponseEntity<>(representante, HttpStatus.OK);
    }

    @RequestMapping(value = "/representante/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRepresentante(@PathVariable("codigo") Integer codigo) {
        representanteService.removerRepresentante(codigo);
    }

    @RequestMapping(value = "/criarEditarRepresentante", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void criarEditarRepresentante(@RequestBody Representante representante) {
        representanteService.criarEditarRepresentante(representante);
    }
}
