package br.com.devdiegopirutti.mainactivity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BaseResponse {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<ItemsItem> items;

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<ItemsItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return
                "BaseResponse{" +
                        "total_count = '" + totalCount + '\'' +
                        ",incomplete_results = '" + incompleteResults + '\'' +
                        ",items = '" + items + '\'' +
                        "}";
    }
}