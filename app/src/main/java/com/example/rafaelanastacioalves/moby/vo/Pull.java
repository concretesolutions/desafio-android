package com.example.rafaelanastacioalves.moby.vo;


import com.example.rafaelanastacioalves.moby.database.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

@Table(database = AppDatabase.class)
public class Pull extends BaseModel implements Serializable {


    @Column
    private String creator;

    @Column
    private String repoName;

    @PrimaryKey
    private int id;

    @Column
    private int order;

    @Column
    @SerializedName("title")
    private String title;

    @SerializedName("user")
    @ForeignKey(saveForeignKeyModel = true)
    private PullUser pullUser;

    @Column
    @SerializedName("body")
    private String body;

    @Column
    @SerializedName("html_url")
    private String pullUrl;


    public Pull() {
    }


    public String getTitle() {
        return title;
    }


    public String getBody() {
        return body;
    }


    public PullUser getPullUser() {
        return pullUser;
    }

    public String getPullUrl() {
        return pullUrl;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPullUser(PullUser pullUser) {
        this.pullUser = pullUser;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPullUrl(String pullUrl) {
        this.pullUrl = pullUrl;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}