package br.com.battycode.dto;

/**
 * Created by fefedo on 14/04/16.
 */
public class Orgao {

    private Integer codigo;
    private String nomeOrgao;
    private Esfera esfera;
    private String email;
    private String cnpj;
    private Integer codigoEndereco;

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
        return esfera;
    }

    public void setEsfera(Esfera esfera) {
        this.esfera = esfera;
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

    public Integer getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Integer codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }
}
