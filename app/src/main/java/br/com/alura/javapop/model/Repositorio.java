package br.com.alura.javapop.model;

public class Repositorio {

    private String nome;
    private String descricao;
    private String nomeUsuario;
    private String sobrenomeUsuario;
    private int quantidadeForks;
    private int quantidadeEstrelas;

    public Repositorio(String nome, String nomeUsuario) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
    }

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

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setSobrenomeUsuario(String sobrenomeUsuario) {
        this.sobrenomeUsuario = sobrenomeUsuario;
    }

    public void setQuantidadeForks(int quantidadeForks) {
        this.quantidadeForks = quantidadeForks;
    }

    public void setQuantidadeEstrelas(int quantidadeEstrelas) {
        this.quantidadeEstrelas = quantidadeEstrelas;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSobrenomeUsuario() {
        return sobrenomeUsuario;
    }

    public int getQuantidadeForks() {
        return quantidadeForks;
    }

    public int getQuantidadeEstrelas() {
        return quantidadeEstrelas;
    }
}
