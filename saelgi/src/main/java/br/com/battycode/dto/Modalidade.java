package br.com.battycode.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.boot.orm.jpa.EntityScan;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fefedo on 14/04/16.
 */
@JsonAutoDetect
@Entity
@Table(name="MODALIDADE")
public class Modalidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;

    @Column(name = "nomemodalidade", nullable = false)
    private String nomeModalidade;

    @Column(name = "flagexcluido", nullable = false,length = 1)
    private String flagExcluido;

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

    public String getFlagExcluido() {
        return flagExcluido;
    }

    public void setFlagExcluido(String flagExcluido) {
        this.flagExcluido = flagExcluido;
    }
}
