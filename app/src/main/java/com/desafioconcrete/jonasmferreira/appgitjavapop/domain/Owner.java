package com.desafioconcrete.jonasmferreira.appgitjavapop.domain;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("id")
    long ID;

    String login;

    @SerializedName("avatar_url")
    String avatarUrl;
    String name;

    public long getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }
}
