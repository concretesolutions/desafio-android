package com.example.rafaelanastacioalves.moby.vo;

import com.example.rafaelanastacioalves.moby.database.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

@Table(database = AppDatabase.class)
public class GitHubUser extends BaseModel implements Serializable {

    @Column
    @SerializedName("login")
    private String login;

    @PrimaryKey
    private int id;

    @Column
    @SerializedName("avatar_url")
    private String avatarUrl;

    @Column
    private String pictureStringData;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPictureStringData() {
        return pictureStringData;
    }

    public void setPictureStringData(String pictureStringData) {
        this.pictureStringData = pictureStringData;
    }
}
