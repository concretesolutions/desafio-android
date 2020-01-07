package br.com.guilherme.concrete.model;

public class Usuario {
    private String nomeUsuario;
    private String nomeCompleto;
    private String fotoUsuario;

    public Usuario(String nomeUsuario, String nomeCompleto, String fotoUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.nomeCompleto = nomeCompleto;
        this.fotoUsuario = fotoUsuario;
    }
}
