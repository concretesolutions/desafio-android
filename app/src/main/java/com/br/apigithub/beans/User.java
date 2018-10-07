package com.br.apigithub.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rlima on 07/10/18.
 */

public class User {
    @SerializedName("id")
    private Integer id;
    @SerializedName("login")
    private String name;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
