package com.desafio.javapop.model;

import com.google.gson.annotations.SerializedName;

public class Repositorio {
    @SerializedName("name")
    private String nomeRepo;

    @SerializedName("description")
    private String descricaoRepo;

    @SerializedName("forks_count")
    private Integer fork;

    @SerializedName("stargazers_count")
    private Integer start;

    @SerializedName("total_count")
    private Integer QtdPaginas;

    @SerializedName("owner")
    private Usuario usuario;

    public Repositorio(String nome, String descricao, Integer fork, Integer start, Integer qtdPaginas, Usuario usuario) {
        this.nomeRepo = nome;
        this.descricaoRepo = descricao;
        this.fork = fork;
        this.start = start;
        QtdPaginas = qtdPaginas;
        this.usuario = usuario;
    }

    public String getNome() {
        return nomeRepo;
    }

    public void setNome(String nome) {
        this.nomeRepo = nome;
    }

    public String getDescricao() {
        return descricaoRepo;
    }

    public void setDescricao(String descricao) {
        this.descricaoRepo = descricao;
    }

    public Integer getFork() { return fork; }

    public void setFork(Integer fork) { this.fork = fork; }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Integer getQtdPaginas() {  return QtdPaginas;   }

    public void setQtdPaginas(Integer qtdPaginas) { QtdPaginas = qtdPaginas; }
}
