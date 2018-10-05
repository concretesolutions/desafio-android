package com.github.api.morepopulargithubapp.model.vo;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@Parcel
public class Repository {

    protected Integer id;
    protected String name;
    protected String description;

    @SerializedName("stargazers_count")
    protected Integer stargazers;
    @SerializedName("forks_count")
    protected Integer forks;

    protected User owner;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getStargazers() {
        return stargazers;
    }

    public void setStargazers(Integer stargazers) {
        this.stargazers = stargazers;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}


