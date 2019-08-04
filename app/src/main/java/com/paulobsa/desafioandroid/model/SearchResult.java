package com.paulobsa.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
    @SerializedName("total_count")
    private long totalCout;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    private List<Item> items;

    public long getTotalCout() {
        return totalCout;
    }

    public void setTotalCout(long totalCout) {
        this.totalCout = totalCout;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
