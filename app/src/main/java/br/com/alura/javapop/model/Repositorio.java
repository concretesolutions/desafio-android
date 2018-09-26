package br.com.alura.javapop.model;

public class Repositorio {

    private String nomeRepositorio;
    private String descricao;

    public Repositorio(String nomeRepositorio, String descricao) {
        this.nomeRepositorio = nomeRepositorio;
        this.descricao = descricao;
    }

    public String getNomeRepositorio() {
        return nomeRepositorio;
    }

    public void setNomeRepositorio(String nomeRepositorio) {
        this.nomeRepositorio = nomeRepositorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
