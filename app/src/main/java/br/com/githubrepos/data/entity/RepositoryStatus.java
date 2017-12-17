package br.com.githubrepos.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RepositoryStatus implements Serializable {

    @SerializedName("total_count")
    private Integer totalCount;

    @SerializedName("incomplete_results")
    private Boolean incompleteResults;

    @SerializedName("items")
    private List<Repository> repositoryList;

    public RepositoryStatus(Integer totalCount, Boolean incompleteResults, List<Repository> repositoryList) {
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.repositoryList = repositoryList;
    }

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

    public List<Repository> getRepositoryList() {
        return repositoryList;
    }

    public void setRepositoryList(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }
}
