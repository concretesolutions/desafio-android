package com.marcos.desafioandroidconcrete.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marco on 16,Abril,2020
 */
public class Repository implements Serializable {
    private String total_count;
    private String incomplete_results;
    private List<RepositoryDetail> items;

    public Repository(String total_count, String incomplete_results, List<RepositoryDetail> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(String incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<RepositoryDetail> getItems() {
        return items;
    }

    public void setItems(List<RepositoryDetail> items) {
        this.items = items;
    }
}
