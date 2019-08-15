package com.desafioconcret.pojo.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PullRequests {
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("user")
    private User user;
    @SerializedName("created_at")
    private Date created_at;

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public Date getCreated_at() {
        return created_at;
    }

}
