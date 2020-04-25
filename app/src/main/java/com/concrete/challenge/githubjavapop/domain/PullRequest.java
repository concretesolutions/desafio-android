package com.concrete.challenge.githubjavapop.domain;

import com.google.gson.annotations.SerializedName;

public class PullRequest {

    public int id;

    public String title;

    public String body;

    public User user;

    public String url;

    @SerializedName("created_at")
    public String createdAt;
}
