package br.com.erico.desafio_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PullRequest implements Serializable {

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("created_at")
    @Expose
    private String date;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("html_url")
    @Expose
    private String pullRequestUrl;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPullRequestUrl() {
        return pullRequestUrl;
    }

    public void setPullRequestUrl(String pullRequestUrl) {
        this.pullRequestUrl = pullRequestUrl;
    }
}
