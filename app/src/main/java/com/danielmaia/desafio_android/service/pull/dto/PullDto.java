package com.danielmaia.desafio_android.service.pull.dto;

import com.danielmaia.desafio_android.model.PullState;
import com.danielmaia.desafio_android.service.list.dto.OwnerDto;

public class PullDto {

    private long id;
    private String title;
    private OwnerDto user;
    private String body;
    private PullState state;
    private String html_url;

    public PullDto() {
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

    public PullState getState() {
        return state;
    }

    public void setState(PullState state) {
        this.state = state;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
