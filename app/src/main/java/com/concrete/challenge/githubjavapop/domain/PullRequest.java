package com.concrete.challenge.githubjavapop.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PullRequest {

    public int id;

    public String title;

    public String body;

    public User user;

    @SerializedName("html_url")
    public String htmlUrl;

    @SerializedName("created_at")
    public Date createdAt;
}
