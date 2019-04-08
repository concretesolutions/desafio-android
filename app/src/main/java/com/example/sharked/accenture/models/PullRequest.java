package com.example.sharked.accenture.models;

import com.google.gson.annotations.SerializedName;

public class PullRequest {
    public String title;
    public String body;
    @SerializedName("user")
    public Owner user;



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

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }

}
