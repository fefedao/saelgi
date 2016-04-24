package br.com.battycode.dao;

import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Modalidade;

import java.util.List;

/**
 * Created by fefedo on 13/04/16.
 */
public interface LicitacaoDAO {

    List<Licitacao> findAllLicitacao();

    Licitacao find(Integer codigoLicitacao);

    void excluirLicitacao(Integer codigoLicitacao);

    void atualizarLicitacao(Licitacao licitacao);

    List<Modalidade> obterModalidades();
}
