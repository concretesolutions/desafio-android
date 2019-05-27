
package com.losingtimeapps.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class RepositoryResponse {

    @SerializedName("incomplete_results")
    private Boolean mIncompleteResults;
    @SerializedName("items")
    private List<Item> mItems;
    @SerializedName("total_count")
    private Long mTotalCount;

    public Boolean getIncompleteResults() {
        return mIncompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        mIncompleteResults = incompleteResults;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

    public Long getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(Long totalCount) {
        mTotalCount = totalCount;
    }

}
