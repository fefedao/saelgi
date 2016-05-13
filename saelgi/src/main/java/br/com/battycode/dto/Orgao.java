package br.com.battycode.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by fefedo on 14/04/16.
 */
@JsonAutoDetect
@Entity
@Table(name="ORGAO")
public class Orgao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;

    @Column(name = "nomeorgao", nullable = false)
    private String nomeOrgao;

    @Column(name = "esfera", nullable = false)
    private Integer esfera;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Endereco endereco;

    @Column(name = "flagexcluido", nullable = false,  length = 1)
    private String flagExcluido;

    @OneToMany(targetEntity = TelefoneOrgao.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="orgao")
    private List telefones;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomeOrgao() {
        return nomeOrgao;
    }

    public void setNomeOrgao(String nomeOrgao) {
        this.nomeOrgao = nomeOrgao;
    }

    public Esfera getEsfera() {
        return Esfera.getEsfera(esfera);
    }

    public void setEsfera(Esfera esfera) {
        this.esfera = esfera.getCodigo();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getFlagExcluido() {
        return flagExcluido;
    }

    public void setFlagExcluido(String flagExcluido) {
        this.flagExcluido = flagExcluido;
    }

    public List getTelefones() {
        return telefones;
    }

    public void setTelefones(List telefones) {
        this.telefones = telefones;
    }
}
