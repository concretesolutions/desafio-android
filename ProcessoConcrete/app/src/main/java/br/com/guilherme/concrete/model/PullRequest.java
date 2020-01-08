package br.com.guilherme.concrete.model;

import com.google.gson.annotations.SerializedName;

public class PullRequest {

    @SerializedName("user")
    private Usuario user;

    @SerializedName("html_url")
    private String pathPR;

    @SerializedName("title")
    private String tituloPR;

    @SerializedName("created_at")
    private String dataPR;

    @SerializedName("body")
    private String bodyPR;

    public Usuario getUser() {
        return user;
    }

    public String getPathPR() {
        return pathPR;
    }

    public String getTituloPR() {
        return tituloPR;
    }

    public String getDataPR() {
        return dataPR;
    }

    public String getBodyPR() {
        return bodyPR;
    }
}
