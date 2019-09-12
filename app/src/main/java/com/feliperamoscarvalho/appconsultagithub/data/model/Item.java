package com.feliperamoscarvalho.appconsultagithub.data.model;


import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("owner")
    private Owner owner;

    @SerializedName("stargazers_count")
    private int stargazersCount;

    @SerializedName("forks_count")
    private int forksCount;

    public Item(int id, String name, String description, Owner owner, int stargazersCount, int forksCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.stargazersCount = stargazersCount;
        this.forksCount = forksCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
}
