package br.com.battycode.service;

import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Modalidade;
import br.com.battycode.dto.Orgao;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by fefedo on 14/04/16.
 */
public interface LicitacaoService {

    void removerLicitacao(Integer codigo);

    void criarEditarLicitacao(Licitacao licitacao) throws IOException;

    List<Modalidade> obterTodasModalidades();

    List<Licitacao> obterTodasLicitacoes();

    Licitacao obterLicitacao(Integer codigo);
}
