package com.paulobsa.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Owner implements Serializable {

    @SerializedName("login")
    private String userName;
    private String userFullName;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
