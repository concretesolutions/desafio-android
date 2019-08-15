package com.desafioconcret.pojo.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRepositorios {

    @SerializedName("items")
    @Expose
    private List<Repositories> repositories = null;

    public List<Repositories> getRepositories() {
        return repositories;
    }

}
