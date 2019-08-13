package com.desafioconcret.pojo.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Head {
    @SerializedName("label")
    private String label;
    @SerializedName("ref")
    private String ref;
    @SerializedName("sha")
    private String sha;
    @SerializedName("user")
    private User user;
    @SerializedName("repo")
    private Repo repo;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }
}
