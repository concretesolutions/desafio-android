package com.example.sharked.accenture.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Owner implements Serializable {
    public Float id;
    public String type;

    @SerializedName("login")
    public String login;
    @SerializedName("avatar_url")
    public String avatarUrl;


    public Float getId() {
        return id;
    }

    public void setId(Float id) {
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



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
