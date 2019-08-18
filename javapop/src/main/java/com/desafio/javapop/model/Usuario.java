package com.desafio.javapop.model;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("id")
    private Integer id;

    @SerializedName("login")
    private String usuario;

    @SerializedName("avatar_url")
    private String imagem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getImagem() { return imagem; }

    public void setImagem(String imagem) { this.imagem = imagem; }

    public Usuario(Integer id, String usuario, String imagem) {
        this.id = id;
        this.usuario = usuario;
        this.imagem = imagem;
    }
}
