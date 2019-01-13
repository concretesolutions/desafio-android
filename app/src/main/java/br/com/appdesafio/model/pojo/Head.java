package br.com.appdesafio.model.pojo;

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
    private Owner user;
    @SerializedName("repo")
    @Expose
    private Item repo;

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

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }

    public Item getRepo() {
        return repo;
    }

    public void setRepo(Item repo) {
        this.repo = repo;
    }

}
