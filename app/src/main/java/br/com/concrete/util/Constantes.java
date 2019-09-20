package br.com.concrete.util;

public final class Constantes {

    private Constantes(){}

    // Address
    public static final String URL_BASE = "https://api.github.com/";
    public static final String URL_REPOSITORIES = "/search/repositories?q=language:Java&sort=stars";
    public static final String URL_PULL_REQUESTES = "/repos/{login}/{name}/pulls";
    // -> https://api.github.com/repos/<owner login>/<name repositório>/pulls

    // Outros
    public static final String SP = "sp";
    public static final String USUARIO = "usuario";
    public static final String AGUARDE = "Aguarde...";
}