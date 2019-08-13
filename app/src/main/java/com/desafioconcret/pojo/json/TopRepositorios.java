package com.desafioconcret.pojo.json;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRepositorios {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;

    @SerializedName("items")
    @Expose
    private List<Repositories> repositories = null;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<Repositories> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repositories> repositories) {
        this.repositories = repositories;
    }
}
