package br.com.guilherme.concrete.model;

import com.google.gson.annotations.SerializedName;

public class Repositorio {

    @SerializedName("name")
    private String nomeRepositorio;

    @SerializedName("description")
    private String descricaoRespositorio;

    @SerializedName("stargazers_count")
    private int qtdBranches;

    @SerializedName("stargazers_count")
    private int qtdFavoritos;

    public String getNomeRepositorio() {
        return nomeRepositorio;
    }

    public void setNomeRepositorio(String nomeRepositorio) {
        this.nomeRepositorio = nomeRepositorio;
    }

    public String getDescricaoRespositorio() {
        return descricaoRespositorio;
    }

    public void setDescricaoRespositorio(String descricaoRespositorio) {
        this.descricaoRespositorio = descricaoRespositorio;
    }

    public int getQtdBranches() {
        return qtdBranches;
    }

    public void setQtdBranches(int qtdBranches) {
        this.qtdBranches = qtdBranches;
    }

    public int getQtdFavoritos() {
        return qtdFavoritos;
    }

    public void setQtdFavoritos(int qtdFavoritos) {
        this.qtdFavoritos = qtdFavoritos;
    }
}

