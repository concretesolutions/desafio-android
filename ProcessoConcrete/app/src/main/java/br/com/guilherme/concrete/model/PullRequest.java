package br.com.guilherme.concrete.model;

import com.google.gson.annotations.SerializedName;

public class PullRequest {

    @SerializedName("user")
    private Usuario user;

    @SerializedName("title")
    private String tituloPR;

    @SerializedName("created_at")
    private String dataPR;

    @SerializedName("body")
    private String bodyPR;

    public Usuario getUser() {
        return user;
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
