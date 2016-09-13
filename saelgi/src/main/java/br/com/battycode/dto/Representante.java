package br.com.battycode.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by fefedo on 13/09/16.
 * (codigo, nmRepresentante, cpf, endereco, telefone, email, flagExcluido)
 */
@JsonAutoDetect
@Entity
@Table(name="REPRESENTANTE")
public class Representante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Endereco endereco;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "flagexcluido", nullable = false,  length = 1)
    private String flagExcluido;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlagExcluido() {
        return flagExcluido;
    }

    public void setFlagExcluido(String flagExcluido) {
        this.flagExcluido = flagExcluido;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
