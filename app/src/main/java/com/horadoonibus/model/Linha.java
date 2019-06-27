package com.horadoonibus.model;

/**
 * Created by Wolszczak on 27/06/2019.
 */
public class Linha {

    private String codigo;
    private String nome;
    private String categoria;
    private String cor;

    public Linha(){

    }

    public Linha(String codigo, String nome, String categoria, String cor){
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.cor =  cor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
