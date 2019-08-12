package com.example.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

public class Pull {

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("user")
    private Owner owner;

    @SerializedName("created_at")
    private String created_at;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
