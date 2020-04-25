package com.concrete.challenge.githubjavapop.domain;

import com.google.gson.annotations.SerializedName;

public class User {

    public int id;

    public String  login;

    public String name;

    @SerializedName("avatar_url")
    public String avatarUrl;
}
