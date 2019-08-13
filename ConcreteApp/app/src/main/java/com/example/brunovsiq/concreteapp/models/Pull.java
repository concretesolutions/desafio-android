package com.example.brunovsiq.concreteapp.models;

import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

public class Pull {

    private String pullTitle;
    private String pullBody;
    private String username;
    private String fullname;
    private String profilePictureUrl;
    private String url;

    public Pull(String pullTitle, String pullBody, String username, String fullname, String profilePictureUrl, String url) {
        this.pullTitle = pullTitle;
        this.pullBody = pullBody;
        this.username = username;
        this.fullname = fullname;
        this.profilePictureUrl = profilePictureUrl;
        this.url = url;
    }

    public String getPullTitle() {
        return pullTitle;
    }

    public void setPullTitle(String pullTitle) {
        this.pullTitle = pullTitle;
    }

    public String getPullBody() {
        return pullBody;
    }

    public void setPullBody(String pullBody) {
        this.pullBody = pullBody;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
