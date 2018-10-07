package com.br.apigithub.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rlima on 04/10/18.
 */

public class Pull {
    @SerializedName("id")
    private Integer id;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String description;
    @SerializedName("user")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
