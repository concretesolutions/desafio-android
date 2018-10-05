package com.github.api.morepopulargithubapp.model.vo;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PullRequest {

    protected Integer id;
    protected String title;
    protected String body;
    protected User user;

    @SerializedName("html_url")
    protected String urlWebSite;


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrlWebSite() {
        return urlWebSite;
    }

    public void setUrlWebSite(String urlWebSite) {
        this.urlWebSite = urlWebSite;
    }
}
