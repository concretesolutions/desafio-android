package br.com.githubjavapop.entidade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repositorio {

    @SerializedName("name")
    @Expose
    private String nomeRepositorio;
    @SerializedName("owner")
    @Expose
    private Usuario usuario;
    @SerializedName("description")
    @Expose
    private String descricao;
    @SerializedName("stargazers_count")
    @Expose
    private int stars;
    @SerializedName("forks")
    @Expose
    private int forks;

    public Repositorio() {

    }

    public Repositorio(String nomeRepositorio, Usuario usuario, String descricao, int stars, int forks) {
        this.nomeRepositorio = nomeRepositorio;
        this.usuario = usuario;
        this.descricao = descricao;
        this.stars = stars;
        this.forks = forks;
    }

    public String getNomeRepositorio() {
        return nomeRepositorio;
    }

    public void setNomeRepositorio(String nomeRepositorio) {
        this.nomeRepositorio = nomeRepositorio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }
}
