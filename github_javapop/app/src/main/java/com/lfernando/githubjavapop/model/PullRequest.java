package com.lfernando.githubjavapop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PullRequest {
    @SerializedName("html_url")
    String url;

    @SerializedName("user")
    User user;

    @SerializedName("title")
    String title;

    @SerializedName("body")
    String body;

    @SerializedName("state")
    String state;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
