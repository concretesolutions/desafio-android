package com.postulacion.githubjavapop.model;


import com.google.gson.annotations.SerializedName;

public class PullRequest {
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String description;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("user")
    private User user;

    public PullRequest(String title, String description, String htmlUrl, User user) {
        this.title = title;
        this.description = description;
        this.htmlUrl = htmlUrl;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public User getUser() {
        return user;
    }
}
