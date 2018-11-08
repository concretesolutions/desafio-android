package com.example.luisguzman.desafio_android.modal;

import com.google.gson.annotations.SerializedName;

public class OwnerList {

    private String id;

    private String login;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("repos_url")
    private String repos;

    public OwnerList(String id, String login, String avatar, String repos) {
        this.id = id;
        this.login = login;
        this.avatar = avatar;
        this.repos = repos;
    }

    public OwnerList() {
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
