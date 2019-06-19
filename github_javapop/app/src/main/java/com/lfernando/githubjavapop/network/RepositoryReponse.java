package com.lfernando.githubjavapop.network;

import com.google.gson.annotations.SerializedName;
import com.lfernando.githubjavapop.model.Repo;

import java.util.List;

public class RepositoryReponse {
    @SerializedName("items")
    List<Repo> items;

    public List<Repo> getItems() {
        return items;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }
}
