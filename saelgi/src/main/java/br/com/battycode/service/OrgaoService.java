package br.com.battycode.service;

import br.com.battycode.dto.Endereco;
import br.com.battycode.dto.Orgao;

import java.util.List;

/**
 * Created by fefedo on 26/04/16.
 */
public interface OrgaoService {

    List<Orgao> obterOrgaos();

    Orgao obterOrgao(Integer codigo);

    void removerOrgao(Integer codigo);

    void criarEditarOrgao(Orgao orgao);

    void editarEndereco(Endereco endereco);

    Endereco obterEndereco(Integer codigo);
}
