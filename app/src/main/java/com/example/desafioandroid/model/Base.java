package com.example.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Base {

    @SerializedName("total_count")
    private int total_count;

    @SerializedName("items")
    private List<Repository> repositories;

    public Base() {
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }
}
