package com.igormeira.githubpop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repositories {

    @SerializedName("items")
    private List<Repository> repositories;

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }
}
