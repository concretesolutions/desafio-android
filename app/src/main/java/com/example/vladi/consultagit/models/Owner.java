package com.example.vladi.consultagit.models;

import com.google.gson.annotations.SerializedName;

public class Owner {

    private String id;

    private String login;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("repos_url")
    private String repos;

    public Owner(String id, String login, String avatar, String repos) {
        this.id = id;
        this.login = login;
        this.avatar = avatar;
        this.repos = repos;
    }

    public Owner() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRepos() {
        return repos;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }
}
