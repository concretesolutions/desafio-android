package com.brunorfreitas.desafioconcrete.data.Model;

import java.util.List;

public class RepositoryResponse {

    private Integer totalCount;
    private Boolean incompleteResults;
    private List<Item> items = null;

    public RepositoryResponse() {
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
