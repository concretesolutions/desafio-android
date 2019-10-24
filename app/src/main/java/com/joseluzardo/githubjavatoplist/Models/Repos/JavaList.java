package com.joseluzardo.githubjavatoplist.Models.Repos;

import java.util.List;

public class JavaList {

    private int total_count;
    private boolean incomplete_result;
    private List<ItemRepos> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_result() {
        return incomplete_result;
    }

    public void setIncomplete_result(boolean incomplete_result) {
        this.incomplete_result = incomplete_result;
    }

    public List<ItemRepos> getItems() {
        return items;
    }

    public void setItems(List<ItemRepos> items) {
        this.items = items;
    }
}
