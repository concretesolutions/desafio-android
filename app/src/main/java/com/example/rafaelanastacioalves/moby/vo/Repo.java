package com.example.rafaelanastacioalves.moby.vo;

import com.example.rafaelanastacioalves.moby.database.AppDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = AppDatabase.class)
public class Repo extends BaseModel {

    @PrimaryKey
    private int id;

    @PrimaryKey
    private String page;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @SerializedName("stargazers_count")
    private int stargazersCount;

    @Column
    private int forks;

    @ForeignKey(saveForeignKeyModel = true)
    private RepoOwner owner;

    @Column
    private String pullsUrl;

    @SuppressWarnings("FieldCanBeLocal")
    @Column
    private String pictureFile;

    public Repo() {
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public int getForks() {
        return forks;
    }

    public GitHubUser getOwner() {
        return owner;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public void setOwner(RepoOwner owner) {
        this.owner = owner;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    public String getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }


    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
