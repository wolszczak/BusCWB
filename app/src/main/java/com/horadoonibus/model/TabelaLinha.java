package com.horadoonibus.model;

/**
 * Created by Wolszczak on 27/06/2019.
 */
public class TabelaLinha {

    private String hora;
    private String ponto;
    private int dia;
    private int num;
    private int tabela;
    private String adapt;
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
