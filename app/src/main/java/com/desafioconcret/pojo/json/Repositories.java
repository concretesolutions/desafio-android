package com.desafioconcret.pojo.json;

import com.google.gson.annotations.SerializedName;

public class Repositories {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String full_name;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("description")
    private String description;
    @SerializedName("stargazers_count")
    private Integer stargazersCount;
    @SerializedName("forks")
    private Integer forks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public Integer getForks() {
        return forks;
    }
}
