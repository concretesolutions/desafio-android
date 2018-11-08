package com.example.luisguzman.desafio_android.modal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataList {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private ArrayList<RepoList> repos;

    public DataList() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public ArrayList<RepoList> getRepos() {
        return repos;
    }

    public void setRepos(ArrayList<RepoList> repos) {
        this.repos = repos;
    }
}