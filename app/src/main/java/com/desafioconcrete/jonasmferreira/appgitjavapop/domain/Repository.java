package com.desafioconcrete.jonasmferreira.appgitjavapop.domain;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("id")
    long ID;

    String name;
    String description;

    @SerializedName("stargazers_count")
    int stargazersCount;

    @SerializedName("forks_count")
    int forksCount;

    Owner owner;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
