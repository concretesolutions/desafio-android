package br.com.githubjavapop.entidade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PullRequest {

    @SerializedName("html_url")
    @Expose
    private String htmlURL;
    @SerializedName("title")
    @Expose
    private String titulo;
    @SerializedName("user")
    @Expose
    private Usuario usuario;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created_at")
    @Expose
    private Date data;

    public PullRequest(String htmlURL, String titulo, Usuario usuario, String body, Date data) {
        this.htmlURL = htmlURL;
        this.titulo = titulo;
        this.usuario = usuario;
        this.body = body;
        this.data = data;
    }

    public String getHtmlURL() {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
