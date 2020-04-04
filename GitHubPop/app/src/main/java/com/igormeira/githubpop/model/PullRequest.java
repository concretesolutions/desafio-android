package com.igormeira.githubpop.model;

import com.google.gson.annotations.SerializedName;

public class PullRequest {

    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String description;
    @SerializedName("url")
    private int link;
    @SerializedName("created_at")
    private int date;
    @SerializedName("user")
    private User user;

}
