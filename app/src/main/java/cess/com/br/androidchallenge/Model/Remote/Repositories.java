package cess.com.br.androidchallenge.Model.Remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Repositories {

    @SerializedName("total_count")
    @Expose
    private int total_count;

    @SerializedName("incomplete_results")
    @Expose
    private Boolean incomplete_results;

    @SerializedName("items")
    @Expose
    private ArrayList<Repo> repos = new ArrayList<>();

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public ArrayList<Repo> getRepos() {
        return repos;
    }

    public void setRepos(ArrayList<Repo> repos) {
        this.repos = repos;
    }
}
