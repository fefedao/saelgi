package br.com.battycode.dto;

import br.com.battycode.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

@JsonAutoDetect
public class Licitacao {

    private Integer codigo;
    private Integer codigoOrgao;
    private Integer codigoModalidade;
    private String numeroEdital;
    private Date dataDeAbertura;
    private Date dataEntregaProposta;
    private Date dataEntregaDocumentacao;

    public Licitacao() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoOrgao() {
        return codigoOrgao;
    }

    public void setCodigoOrgao(Integer codigoOrgao) {
        this.codigoOrgao = codigoOrgao;
    }

    public Integer getCodigoModalidade() {
        return codigoModalidade;
    }

    public void setCodigoModalidade(Integer codigoModalidade) {
        this.codigoModalidade = codigoModalidade;
    }

    public String getNumeroEdital() {
        return numeroEdital;
    }

    public void setNumeroEdital(String numeroEdital) {
        this.numeroEdital = numeroEdital;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getDataDeAbertura() {
        return dataDeAbertura;
    }

    public void setDataDeAbertura(Date dataDeAbertura) {
        this.dataDeAbertura = dataDeAbertura;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getDataEntregaProposta() {
        return dataEntregaProposta;
    }

    public void setDataEntregaProposta(Date dataEntregaProposta) {
        this.dataEntregaProposta = dataEntregaProposta;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getDataEntregaDocumentacao() {
        return dataEntregaDocumentacao;
    }

    public void setDataEntregaDocumentacao(Date dataEntregaDocumentacao) {
        this.dataEntregaDocumentacao = dataEntregaDocumentacao;
    }


}
