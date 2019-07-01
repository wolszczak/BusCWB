package com.buscwb.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Wolszczak on 27/06/2019.
 */
@DatabaseTable(tableName = "TabelaLinha")
public class TabelaLinha implements Serializable {

    @DatabaseField(columnName = "hora")
    private String hora;
    @DatabaseField(columnName = "ponto")
    private String ponto;
    @DatabaseField(columnName = "dia")
    private int dia;
    @DatabaseField(columnName = "num")
    private int num;
    @DatabaseField(columnName = "tabela")
    private int tabela;
    @DatabaseField(columnName = "adapt")
    private String adapt;
    @DatabaseField(columnName = "codigolinha")
    private String codigoLinha;

    public TabelaLinha() {
    }

    public TabelaLinha(String hora, String ponto, int dia, int num, int tabela, String adapt, String codigoLinha) {
        this.hora = hora;
        this.ponto = ponto;
        this.dia = dia;
        this.num = num;
        this.tabela = tabela;
        this.adapt = adapt;
        this.codigoLinha = codigoLinha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPonto() {
        return ponto;
    }

    public void setPonto(String ponto) {
        this.ponto = ponto;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTabela() {
        return tabela;
    }

    public void setTabela(int tabela) {
        this.tabela = tabela;
    }

    public String getAdapt() {
        return adapt;
    }

    public void setAdapt(String adapt) {
        this.adapt = adapt;
    }

    public String getCodigoLinha() {
        return codigoLinha;
    }

    public void setCodigoLinha(String codigoLinha) {
        this.codigoLinha = codigoLinha;
    }
}
