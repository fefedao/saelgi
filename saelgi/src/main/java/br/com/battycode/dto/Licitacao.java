package br.com.battycode.dto;

import java.util.Date;
import java.util.List;

public class Licitacao {

	String orgao;
	String uf;
	Esfera esfera;
	Endereco endereco;
	Proposta propostaComercial;
	List<Documento> documentacao;
	Date dataDeAbertura;
	Date dataEntregaProposta;
	Date dataEntregaDocumentacao;
	
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
	public Date getDataDeAbertura() {
		return dataDeAbertura;
	}
	public void setDataDeAbertura(Date dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}
	public Date getDataEntregaProposta() {
		return dataEntregaProposta;
	}
	public void setDataEntregaProposta(Date dataEntregaProposta) {
		this.dataEntregaProposta = dataEntregaProposta;
	}
	public Date getDataEntregaDocumentacao() {
		return dataEntregaDocumentacao;
	}
	public void setDataEntregaDocumentacao(Date dataEntregaDocumentacao) {
		this.dataEntregaDocumentacao = dataEntregaDocumentacao;
	}
	
	
	
}
