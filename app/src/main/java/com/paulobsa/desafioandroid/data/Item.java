package com.paulobsa.desafioandroid.data;

import com.google.gson.annotations.SerializedName;

public class Item {

    private Long id;
    private String name;
    private String description;
    @SerializedName("forks_count")
    private Integer forkCount;

    @SerializedName("stargazers_count")
    private Integer favCount;

    private Owner owner;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getForkCount() {
        return forkCount;
    }

    public void setForkCount(Integer forkCount) {
        this.forkCount = forkCount;
    }

    public Integer getFavCount() {
        return favCount;
    }

    public void setFavCount(Integer favCount) {
        this.favCount = favCount;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
