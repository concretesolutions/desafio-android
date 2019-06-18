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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
