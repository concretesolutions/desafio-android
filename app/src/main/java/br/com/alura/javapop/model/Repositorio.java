package br.com.alura.javapop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repositorio implements Serializable{

    @JsonProperty("name")
    private String nome;

    @JsonProperty("description")
    private String descricao;

    @JsonProperty("owner")
    private Usuario usuario;

    @JsonProperty("forks")
    private int quantidadeForks;

    @JsonProperty("stargazers_count")
    private int quantidadeEstrelas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidadeForks(int quantidadeForks) {
        this.quantidadeForks = quantidadeForks;
    }

    public void setQuantidadeEstrelas(int quantidadeEstrelas) {
        this.quantidadeEstrelas = quantidadeEstrelas;
    }

    public int getQuantidadeForks() {
        return quantidadeForks;
    }

    public int getQuantidadeEstrelas() {
        return quantidadeEstrelas;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
