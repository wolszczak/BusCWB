package com.horadoonibus.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Wolszczak on 29/06/2019.
 */
public class ShapeLinha implements Serializable {

    @SerializedName("SHP")
    private String shp;
    @SerializedName("LAT")
    private Double latitude;
    @SerializedName("LON")
    private Double longitude;
    @SerializedName("COD")
    private String linha;

    public ShapeLinha() {
    }

    public String getShp() {
        return shp;
    }

    public void setShp(String shp) {
        this.shp = shp;
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

    public ShapeLinha(String shp, Double latitude, Double longitude, String linha) {
        this.shp = shp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.linha = linha;
    }
}
