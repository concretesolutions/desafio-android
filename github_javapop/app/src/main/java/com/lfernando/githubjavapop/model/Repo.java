package com.lfernando.githubjavapop.model;

import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("name")
    String name;

    @SerializedName("html_url")
    String url;

    @SerializedName("description")
    String description;

    @SerializedName("stargazers_count")
    int stars;

    @SerializedName("forks_count")
    int forks;

    @SerializedName("owner")
    User owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
