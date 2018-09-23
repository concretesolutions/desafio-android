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
}
