package com.github.api.morepopulargithubapp.model.vo;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by ramon on 01/08/18.
 */

@Parcel
public class User {

    protected String login;
    protected Integer id;
    @SerializedName("avatar_url")
    protected String photo;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
