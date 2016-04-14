package br.com.battycode.dao;

import br.com.battycode.dto.Licitacao;

import java.util.List;

/**
 * Created by fefedo on 13/04/16.
 */
public interface LicitacaoDAO {

    List<Licitacao> findAll();

}
