package com.felipemiranda.desafioconcrete.model.response;

import com.felipemiranda.desafioconcrete.model.Item;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by felipemiranda
 */

public class SearchResponse implements Serializable {

    @SerializedName("total_count")
    private Integer totalCount;
    private Boolean incompleteResults;
    private ArrayList<Item> items;

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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
