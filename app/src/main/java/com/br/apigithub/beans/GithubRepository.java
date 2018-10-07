package com.br.apigithub.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rlima on 04/10/18.
 */

public class GithubRepository {
    @SerializedName("items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
