package br.com.githubjavapop.entidade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("login")
    @Expose
    private String username;
    @SerializedName("avatar_url")
    @Expose
    private String foto;

    public Usuario(String username, String nome, String foto) {
        this.username = username;
        this.foto = foto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
