package br.com.devdiegopirutti.mainactivity.models;

import com.google.gson.annotations.SerializedName;

public class Base {

    @SerializedName("ref")
    private String ref;

    @SerializedName("repo")
    private Repo repo;

    @SerializedName("label")
    private String label;

    @SerializedName("sha")
    private String sha;

    @SerializedName("user")
    private User user;

    public String getRef() {
        return ref;
    }

    public Repo getRepo() {
        return repo;
    }

    public String getLabel() {
        return label;
    }

    public String getSha() {
        return sha;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return
                "Base{" +
                        "ref = '" + ref + '\'' +
                        ",repo = '" + repo + '\'' +
                        ",label = '" + label + '\'' +
                        ",sha = '" + sha + '\'' +
                        ",user = '" + user + '\'' +
                        "}";
    }
}