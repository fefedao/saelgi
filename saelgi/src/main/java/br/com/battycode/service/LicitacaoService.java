package br.com.battycode.service;

import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Modalidade;
import br.com.battycode.dto.Orgao;

import java.util.List;

/**
 * Created by fefedo on 14/04/16.
 */
public interface LicitacaoService {

    List<Licitacao> obterTodasLicitacoes();

    Licitacao obterLicitacao(Integer codigo);

    void removerLicitacao(Integer codigo);

    void criarEditarLicitacao(Licitacao licitacao);

    List<Modalidade> obterTodasModalidades();

    List<Orgao> obterOrgaos();

    List<Licitacao> obterTodasLicitacoesRepository();
}
