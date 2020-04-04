package com.igormeira.githubpop.model;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("name")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("stargazers_count")
    private int stars;
    @SerializedName("forks_count")
    private int forks;
    @SerializedName("owner")
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStars() {
        return String.valueOf(stars);
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getForks() {
        return String.valueOf(forks);
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
