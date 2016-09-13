package br.com.battycode.service;

import br.com.battycode.dto.Representante;

import java.util.List;

/**
 * Created by fefedo on 13/09/16.
 */
public interface RepresentanteService {

    List<Representante> obterTodosRepresentante();

    void removerRepresentante(Integer codigo);

    void criarEditarRepresentante(Representante representante);

    Representante obterRepresentante(Integer codigo);
}
