package br.com.battycode.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fefedo on 13/05/16.
 */

@JsonAutoDetect
@Entity
@Table(name="TELEFONEORGAO")
public class TelefoneOrgao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "flprincipal", nullable = false, length = 1)
    private String flprincipal;

    @Column(name = "flagexcluido", nullable = false,  length = 1)
    private String flagExcluido;

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    private Orgao orgao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFlprincipal() {
        return flprincipal;
    }

    public void setFlprincipal(String flprincipal) {
        this.flprincipal = flprincipal;
    }

    public String getFlagExcluido() {
        return flagExcluido;
    }

    public void setFlagExcluido(String flagExcluido) {
        this.flagExcluido = flagExcluido;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }
}

