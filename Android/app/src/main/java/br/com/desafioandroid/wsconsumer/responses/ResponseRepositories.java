package br.com.desafioandroid.wsconsumer.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.desafioandroid.model.Repository;

public class ResponseRepositories {
    @SerializedName("total_count")
    int total_count;

    @SerializedName("incomplete_results")
    boolean incomplete_results;

    @SerializedName("items")
    List<Repository> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Repository> getItems() {
        return items;
    }

    public void setItems(List<Repository> items) {
        this.items = items;
    }
}
