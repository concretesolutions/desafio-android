package com.igormeira.githubpop.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
