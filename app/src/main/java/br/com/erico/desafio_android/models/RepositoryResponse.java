package br.com.erico.desafio_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoryResponse {

    @SerializedName("items")
    @Expose
    private List<Repository> repositories = null;

    @SerializedName("total_count")
    @Expose
    private Integer numRepositories;

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public Integer getNumRepositories() {
        return numRepositories;
    }

    public void setNumRepositories(Integer numRepositories) {
        this.numRepositories = numRepositories;
    }
}
