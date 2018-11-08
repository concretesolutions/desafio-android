package com.example.luisguzman.desafio_android.modal;

import com.google.gson.annotations.SerializedName;

public class UserList {

    @SerializedName("login")
    private String user;

    @SerializedName("avatar_url")
    private String avatar;

    public UserList(String user, String avatar) {
        this.user = user;
        this.avatar = avatar;
    }

    public UserList() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
