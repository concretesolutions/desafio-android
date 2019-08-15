package br.com.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

public class PullsRequests {
    @SerializedName("html_url")
    String url;
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String body;
    @SerializedName("user")
    OwnerRepo user;
    @SerializedName("created_at")
    String created_at;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public OwnerRepo getUser() {
        return user;
    }

    public void setUser(OwnerRepo user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
