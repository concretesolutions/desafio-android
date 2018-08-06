package com.example.lucas.concrete_solutions_desafio.model;

import java.util.Date;

public class PullRequest {
    private String title;
    private String body;
    private Date created_at;

    private User user;

    public PullRequest(String title, String body, Date created_at, User user) {
        this.title = title;
        this.body = body;
        this.created_at = created_at;
        this.user = user;
    }

    public PullRequest() {

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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
