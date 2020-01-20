package com.example.desafio_concrete.networkUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("login")
    @Expose
    private String nameAuthor;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;


    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "login='" + nameAuthor + '\'' +
                ", avatar_url='" +avatarUrl + '\'' +
                "}";
    }
}
