package br.com.guilherme.concrete.model;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("login")
    private String nomeUsuario;

    @SerializedName("avatar_url")
    private String fotoUsuario;

    public Usuario(String nomeUsuario, String fotoUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.fotoUsuario = fotoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }
}
