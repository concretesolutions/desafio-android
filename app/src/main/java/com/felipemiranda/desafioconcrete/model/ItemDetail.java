package com.felipemiranda.desafioconcrete.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by felipemiranda
 */

public class ItemDetail implements Serializable {

    private String title;
    private Owner user;
    @SerializedName("created_at")
    private String created_at;
    private String body;
    @SerializedName("html_url")
    private String html_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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
}
