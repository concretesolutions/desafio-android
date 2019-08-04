package com.paulobsa.desafioandroid.model;

import java.io.Serializable;

public class PullRequest implements Serializable {
    private String title;
    private String body;
    private String state;
    private User user;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getState() {
        return state;
    }

    public User getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
