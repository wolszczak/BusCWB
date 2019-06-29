package com.horadoonibus.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Wolszczak on 29/06/2019.
 */
public class Veiculos implements Serializable {

    @SerializedName("COD")
    private String codigo;
    @SerializedName("REFRESH")
    private String refresh;
    @SerializedName("LAT")
    private Long latitude;
    @SerializedName("LON")
    private String longitude;
    @SerializedName("CODIGOLINHA")
    private String linha;
    @SerializedName("ADAPT")
    private String adaptado;
    @SerializedName("TIPO_VEIC")
    private String tipoVeiculo;
    @SerializedName("TABELA")
    private String tabela;
    @SerializedName("SITUACAO")
    private String situacao;
    @SerializedName("TCOUNT")
    private String tcount;

    public Veiculos() {
    }

    public Veiculos(String codigo, String refresh, Long latitude, String longitude, String linha, String adaptado, String tipoVeiculo, String tabela, String situacao, String tcount) {
        this.codigo = codigo;
        this.refresh = refresh;
        this.latitude = latitude;
        this.longitude = longitude;
        this.linha = linha;
        this.adaptado = adaptado;
        this.tipoVeiculo = tipoVeiculo;
        this.tabela = tabela;
        this.situacao = situacao;
        this.tcount = tcount;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getAdaptado() {
        return adaptado;
    }

    public void setAdaptado(String adaptado) {
        this.adaptado = adaptado;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTcount() {
        return tcount;
    }

    public void setTcount(String tcount) {
        this.tcount = tcount;
    }
}
