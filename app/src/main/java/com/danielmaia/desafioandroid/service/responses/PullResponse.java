package com.danielmaia.desafioandroid.service.responses;

import com.danielmaia.desafioandroid.service.responses.dto.OwnerDto;

public class PullResponse {
    private long id;
    private String title;
    private OwnerDto user;
    private String body;
    private String state;
    private String html_url;

    public PullResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OwnerDto getUser() {
        return user;
    }

    public void setUser(OwnerDto user) {
        this.user = user;
    }

    public String getBody() {
        return body == null ? "" : body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
