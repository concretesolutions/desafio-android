package com.feliperamoscarvalho.appconsultagithub.data.model;

import com.google.gson.annotations.SerializedName;

public class Pull {

    @SerializedName("id")
    private int id;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("user")
    private User user;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("created_at")
    private String createdAt;

    public Pull(int id, String htmlUrl, User user, String title, String body, String createdAt) {
        this.id = id;
        this.htmlUrl = htmlUrl;
        this.user = user;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
