package com.buscwb.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Wolszczak on 27/06/2019.
 */
@DatabaseTable(tableName = "Linha")
public class Linha implements Serializable {

    @SerializedName("COD")
    @DatabaseField(columnName = "COD")
    private String codigo;
    @SerializedName("NOME")
    @DatabaseField(columnName = "NOME")
    private String nome;
    @SerializedName("SOMENTE_CARTAO")
    @DatabaseField(columnName = "SOMENTE_CARTAO")
    private String cartao;
    @SerializedName("CATEGORIA_SERVICO")
    @DatabaseField(columnName = "CATEGORIA_SERVICO")
    private String categoria;
    @SerializedName("NOME_COR")
    @DatabaseField(columnName = "NOME_COR")
    private String cor;

    public Linha(){

    }

    public Linha(String codigo, String nome, String cartao, String categoria, String cor){
        this.codigo = codigo;
        this.nome = nome;
        this.cartao = cartao;
        this.categoria = categoria;
        this.cor =  cor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
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
