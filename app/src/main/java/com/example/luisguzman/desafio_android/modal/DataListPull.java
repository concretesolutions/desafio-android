package com.example.luisguzman.desafio_android.modal;

import com.google.gson.annotations.SerializedName;

public class DataListPull {

    @SerializedName("title")
    private String name;

    @SerializedName("body")
    private String description;

    private UserList user;

    @SerializedName("html_url")
    private String url;

    public DataListPull(String name, String description, UserList user, String url) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.url = url;
    }

    public DataListPull() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserList getUser() {
        return user;
    }

    public void setUser(UserList user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
