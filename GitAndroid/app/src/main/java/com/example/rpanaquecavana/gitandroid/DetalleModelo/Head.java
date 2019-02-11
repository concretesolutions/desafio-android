
package com.example.rpanaquecavana.gitandroid.DetalleModelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Head {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("ref")
    @Expose
    private String ref;
    @SerializedName("sha")
    @Expose
    private String sha;
    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("repo")
    @Expose
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

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

}
