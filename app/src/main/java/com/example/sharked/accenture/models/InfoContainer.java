package com.example.sharked.accenture.models;

import com.google.gson.annotations.SerializedName;

public class InfoContainer {
    @SerializedName("total_count")
    public String totalCount;

    @SerializedName("incomplete_results")
    public boolean incompleteResults;

    @SerializedName("items")
    public Repository[] items;


    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public Repository[] getItems() {
        return items;
    }

    public void setItems(Repository[] items) {
        this.items = items;
    }



}
