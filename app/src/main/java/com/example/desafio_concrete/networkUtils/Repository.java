package com.example.desafio_concrete.networkUtils;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repository {

    @SerializedName("items")
    @Expose
    private List<Item> items =null;

    public List<Item> getItems(){
        return this.items;
    }
    public void setItems(List<Item> items){
        this.items = items;
    }

}
