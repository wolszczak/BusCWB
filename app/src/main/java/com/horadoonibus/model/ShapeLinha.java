package com.horadoonibus.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Wolszczak on 29/06/2019.
 */
public class ShapeLinha implements Serializable {

    @SerializedName("COD")
    private String codigo;
    @SerializedName("REFRESH")
    private String refresh;
    @SerializedName("LAT")
    private Double latitude;
    @SerializedName("LON")
    private Double longitude;
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

    public ShapeLinha() {
    }

    public ShapeLinha(String codigo, String refresh, Double latitude, Double longitude, String linha, String adaptado, String tipoVeiculo, String tabela, String situacao, String tcount) {
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
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
