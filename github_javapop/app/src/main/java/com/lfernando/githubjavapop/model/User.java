package com.lfernando.githubjavapop.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    int id;

    @SerializedName("login")
    String login;

    @SerializedName("avatar_url")
    String avatarUrl;

    @SerializedName("name")
    String name;
}
