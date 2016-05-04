package br.com.battycode.dao;

import br.com.battycode.dto.Endereco;

/**
 * Created by fefedo on 03/05/16.
 */
public interface EnderecoDAO {

    Endereco find(Integer codigoEndereco);

    void editarEndereco(Endereco endereco);

    void criarEndereco(Endereco endereco);
}
