package com.desafioconcrete.jonasmferreira.appgitjavapop.domain;

import com.google.gson.annotations.SerializedName;

public class PullRequest {
    String title;
    String body;

    @SerializedName("created_at")
    String createdAt;
    @SerializedName("html_url")
    String htmlUrl;
    User user;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public User getUser() {
        return user;
    }
}
