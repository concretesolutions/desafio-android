package com.postulacion.githubjavapop.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("login")
    private String userName;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("type")
    private String nickName;

    public User(String userName, String avatarUrl, String nickName) {
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
