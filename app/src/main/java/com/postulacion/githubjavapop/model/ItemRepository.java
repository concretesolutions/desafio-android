package com.postulacion.githubjavapop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemRepository {
    @SerializedName("items")
    private List<Repository> repositoryList;

    public ItemRepository(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    public List<Repository> getRepositoryList() {
        return repositoryList;
    }
}
