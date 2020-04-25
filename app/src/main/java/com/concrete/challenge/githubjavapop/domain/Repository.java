package com.concrete.challenge.githubjavapop.domain;

import com.google.gson.annotations.SerializedName;

public class Repository {

    public Integer id;

    public String name;

    public String description;

    @SerializedName("forks_count")
    public Integer forksCount;

    @SerializedName("stargazers_count")
    public Integer stargazersCount;

    public User owner;
}
