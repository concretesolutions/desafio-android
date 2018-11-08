package com.example.luisguzman.desafio_android.modal;

import com.google.gson.annotations.SerializedName;

public class RepoList {

    private String id;

    private String name;

    private String description;

    @SerializedName("stargazers_count")
    private int stars;

    @SerializedName("forks_count")
    private int forks;

    private OwnerList owner;

    public RepoList(String id, String name, String description, int stars, int forks, OwnerList owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stars = stars;
        this.forks = forks;
        this.owner = owner;
    }

    public RepoList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStars() {
        return Integer.toString(stars);
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getForks() {
        return Integer.toString(forks);
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public OwnerList getOwner() {
        return owner;
    }

    public void setOwner(OwnerList owner) {
        this.owner = owner;
    }
}
