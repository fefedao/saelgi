package br.com.battycode.dto;

import br.com.battycode.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

@JsonAutoDetect
public class Licitacao {

    private Integer codigo;
    private Orgao orgao;
    private Modalidade modalidade;
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

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
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
