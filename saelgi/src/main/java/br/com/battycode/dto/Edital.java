package br.com.battycode.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

/**
 * Created by fefedo on 13/09/16.
 */
@JsonAutoDetect
@Entity
@Table(name="EDITAL")
public class Edital {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;

    @Column(name = "codigolicitacal")
    private Integer codigoLicitacao;

    @Lob
    @Column(columnDefinition = "blob", length=100000, name = "bledital")
    private byte[] blEdital;

    @Column(name = "nmarquivoedital")
    private String nmArquivoEdital;


    public byte[] getBlEdital() {
        return blEdital;
    }

    public void setBlEdital(byte[] blEdital) {
        this.blEdital = blEdital;
    }

    public String getNmArquivoEdital() {
        return nmArquivoEdital;
    }

    public void setNmArquivoEdital(String nmArquivoEdital) {
        this.nmArquivoEdital = nmArquivoEdital;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setCodigoLicitacao(Integer codigoLicitacao) {
        this.codigoLicitacao = codigoLicitacao;
    }

    public Integer getCodigoLicitacao() {

        return codigoLicitacao;
    }


}
