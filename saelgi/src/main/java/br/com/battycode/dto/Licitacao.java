package br.com.battycode.dto;

import br.com.battycode.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

@JsonAutoDetect
public class Licitacao {

	private String orgao;
	private String uf;
	private Esfera esfera;
	private Endereco endereco;
	private Proposta propostaComercial;
	private List<Documento> documentacao;
	private Date dataDeAbertura;
	private Date dataEntregaProposta;
	private Date dataEntregaDocumentacao;
	
	public Licitacao(String orgao, String uf, Esfera esfera, Date dataDeAbertura, Date dataEntregaProposta, Date dataEntregaDocumentacao) {
		this.orgao = orgao;
		this.uf = uf;
		this.esfera = esfera;
		this.dataDeAbertura = dataDeAbertura;
		this.dataEntregaProposta = dataEntregaProposta;
		this.dataEntregaDocumentacao = dataEntregaDocumentacao;
	}
	
	public String getOrgao() {
		return orgao;
	}
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Esfera getEsfera() {
		return esfera;
	}
	public void setEsfera(Esfera esfera) {
		this.esfera = esfera;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Proposta getPropostaComercial() {
		return propostaComercial;
	}
	public void setPropostaComercial(Proposta propostaComercial) {
		this.propostaComercial = propostaComercial;
	}
	public List<Documento> getDocumentacao() {
		return documentacao;
	}
	public void setDocumentacao(List<Documento> documentacao) {
		this.documentacao = documentacao;
	}
    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getDataDeAbertura() {
		return dataDeAbertura;
	}
	public void setDataDeAbertura(Date dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}
    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getDataEntregaProposta() {
		return dataEntregaProposta;
	}
	public void setDataEntregaProposta(Date dataEntregaProposta) {
		this.dataEntregaProposta = dataEntregaProposta;
	}
    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getDataEntregaDocumentacao() {
		return dataEntregaDocumentacao;
	}
	public void setDataEntregaDocumentacao(Date dataEntregaDocumentacao) {
		this.dataEntregaDocumentacao = dataEntregaDocumentacao;
	}
	
	
	
}
