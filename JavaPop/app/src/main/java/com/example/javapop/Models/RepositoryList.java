package com.example.javapop.Models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoryList extends Repository {
    @SerializedName("items")
    private List<Repository> repositoriesList;

    protected RepositoryList(Parcel in) {
        super(in);
    }

    public List<Repository> getRepositoriesList() {
        return repositoriesList;
    }
}
