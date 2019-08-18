package com.desafio.javapop.model;

import com.google.gson.annotations.SerializedName;

public class PullRequest {

    @SerializedName("id")
    private Integer idPull;

    @SerializedName("title")
    private String titulo;

    @SerializedName("created_at")
    private String data;

    @SerializedName("body")
    private String corpo;

    @SerializedName("user") private Usuario usuario;

    public PullRequest(Integer id, String titulo, String data, String corpo,Usuario usuario) {
        this.idPull = id;
        this.titulo = titulo;
        this.data = data;
        this.corpo = corpo;
        this.usuario = usuario;

    }

    public Integer getId() {
        return idPull;
    }

    public void setId(Integer id) {
        this.idPull = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Usuario getUsuario() {   return usuario;   }

    public void setUsuario(Usuario usuario) {    this.usuario = usuario;   }
}
