package br.com.guilherme.concrete.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repositorio{
    @SerializedName("owner")
    private Usuario user;

    @SerializedName("items")
    private List<Repositorio> repositorios;

    @SerializedName("name")
    private String nomeRepositorio;

    @SerializedName("description")
    private String descricaoRespositorio;

    @SerializedName("forks")
    private int qtdBranches;

    @SerializedName("stargazers_count")
    private int qtdFavoritos;

    // Getters and Setters
    public String getNomeRepositorio() {
        return nomeRepositorio;
    }

    public String getDescricaoRespositorio() {
        return descricaoRespositorio;
    }

    public int getQtdBranches() {
        return qtdBranches;
    }

    public int getQtdFavoritos() {
        return qtdFavoritos;
    }

    public List<Repositorio> getRepositorios() {
        return repositorios;
    }

    public Usuario getUser() {
        return user;
    }
}

