package com.igormeira.githubpop.model;

import com.google.gson.annotations.SerializedName;

import static com.igormeira.githubpop.util.StringUtil.formateDateFromstring;

public class PullRequest {

    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String description;
    @SerializedName("url")
    private String link;
    @SerializedName("created_at")
    private String date;
    @SerializedName("user")
    private User user;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        String formattedDate = formateDateFromstring("yyyy-MM-dd", "dd-MMM-yyyy", date);
        return formattedDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
