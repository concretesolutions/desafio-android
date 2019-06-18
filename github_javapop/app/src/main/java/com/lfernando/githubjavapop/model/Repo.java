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
}
