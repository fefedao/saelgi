package br.com.battycode.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by fefedo on 14/04/16.
 */
@JsonAutoDetect
public class Modalidade {

    private Integer codigo;
    private String nomeModalidade;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomeModalidade() {
        return nomeModalidade;
    }

    public void setNomeModalidade(String nomeModalidade) {
        this.nomeModalidade = nomeModalidade;
    }
}
