package com.example.desafioconcrete.Objects;

public class DetailsPull {

    private String title;
    private String body;
    private String html_url;
    private OwnerPull user;
    private String created_at;

    public DetailsPull(String title, String body, String html_url, OwnerPull user, String created_at) {
        this.title = title;
        this.body = body;
        this.html_url = html_url;
        this.user = user;
        this.created_at = created_at;
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

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public OwnerPull getUser() {
        return user;
    }

    public void setUser(OwnerPull user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
