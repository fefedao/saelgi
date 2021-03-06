package br.com.battycode.dao;

import br.com.battycode.dto.Licitacao;
import br.com.battycode.dto.Orgao;

import java.util.List;

/**
 * Created by fefedo on 24/04/16.
 */
public interface OrgaoDAO {

    List<Orgao> findAllOrgaosLicitacao();

    List<Orgao> findAllOrgao();

    Orgao obterOrgao(Integer codigo);

    void removerOrgao(Integer codigo);

    void editarOrgao(Orgao orgao);

    void criarOrgao(Orgao orgao);

}
