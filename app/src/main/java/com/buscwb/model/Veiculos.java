package com.buscwb.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Wolszczak on 29/06/2019.
 */
public class Veiculos implements Serializable {

    @SerializedName("PREFIXO")
    private String prefixo;
    @SerializedName("HORA")
    private String hora;
    @SerializedName("LAT")
    private Double latitude;
    @SerializedName("LON")
    private Double longitude;
    @SerializedName("LINHA")
    private String linha;
    @SerializedName("ADAPT")
    private String adaptado;

    public Veiculos() {
    }

    public Veiculos(String prefixo, String hora, Double latitude, Double longitude, String linha, String adaptado) {
        this.prefixo = prefixo;
        this.hora = hora;
        this.latitude = latitude;
        this.longitude = longitude;
        this.linha = linha;
        this.adaptado = adaptado;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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
}

