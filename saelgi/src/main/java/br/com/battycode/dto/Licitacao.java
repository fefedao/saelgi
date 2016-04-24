package br.com.battycode.dto;

import br.com.battycode.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonAutoDetect
public class Licitacao {

    private Integer codigo;
    private Orgao orgao;
    private Modalidade modalidade;
    private String numeroEdital;
    private Date dataDeAbertura;
    private Date dataEntregaProposta;
    private Date dataEntregaDocumentacao;

    private String dataDeAberturaLong;
    private String dataEntregaPropostaLong;
    private String dataEntregaDocumentacaoLong;

    private String dataDeAberturaText;
    private String dataEntregaPropostaText;
    private String dataEntregaDocumentacaoText;


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
    public Date getDataDeAbertura() { return dataDeAbertura; }

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

    //date angular
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public long getDataDeAberturaLong() {
        return dataDeAbertura.getTime();
    }

    public void setDataDeAberturaLong(String dataDeAberturaLong) {
        try {
            this.dataDeAbertura = dateFormat.parse(dataDeAberturaLong);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public long getDataEntregaPropostaLong() {
        return dataEntregaProposta.getTime();
    }

    public void setDataEntregaPropostaLong(String dataEntregaPropostaLong) {
        try {
            this.dataEntregaProposta = dateFormat.parse(dataEntregaPropostaLong);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public long getDataEntregaDocumentacaoLong() {
        return dataEntregaDocumentacao.getTime();
    }

    public void setDataEntregaDocumentacaoLong(String dataEntregaDocumentacaoLong) {
        try {
            this.dataEntregaDocumentacao = dateFormat.parse(dataEntregaDocumentacaoLong);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //date apresentacao
    private static final SimpleDateFormat dateFormatText = new SimpleDateFormat("dd/MM/yyyy");

    public String getDataDeAberturaText() {
        return dateFormatText.format(this.dataDeAbertura);
    }

    public String getDataEntregaPropostaText() {
        return dateFormatText.format(this.dataEntregaProposta);
    }

    public String getDataEntregaDocumentacaoText() {
        return dateFormatText.format(this.dataEntregaDocumentacao);
    }
}
