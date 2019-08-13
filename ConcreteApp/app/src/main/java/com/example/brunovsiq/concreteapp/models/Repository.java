package com.example.brunovsiq.concreteapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.recyclerview.widget.DiffUtil;

public class Repository extends BaseObservable {

    //private String
    private int id;
    private String repoName;
    private String repoDescription;
    private int forkQuantity;
    private int starsQuantity;
    private String pictureUrl;
    private String username;
    private String fullName;

    public Repository(JSONObject jsonObject) {
        //handle json parameters
        //JSONObject jsonObject1 = jsonObject.getJSONObject("owner");
        try {
            this.repoName = jsonObject.getString("name");
            this.repoDescription = jsonObject.getString("description");
            this.forkQuantity = jsonObject.getInt("forks");
            this.starsQuantity = jsonObject.getInt("stargazers_count");
            JSONObject jsonObject1 = jsonObject.getJSONObject("owner");
            this.pictureUrl = jsonObject1.getString("avatar_url");
            this.username = jsonObject1.getString("login");
            this.fullName = jsonObject1.has("name") ? jsonObject1.getString("name") : "";

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoDescription() {
        return repoDescription;
    }

    public void setRepoDescription(String repoDescription) {
        this.repoDescription = repoDescription;
    }

    public int getForkQuantity() {
        return forkQuantity;
    }

    public void setForkQuantity(int forkQuantity) {
        this.forkQuantity = forkQuantity;
    }

    public int getStarsQuantity() {
        return starsQuantity;
    }

    public void setStarsQuantity(int starsQuantity) {
        this.starsQuantity = starsQuantity;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static final DiffUtil.ItemCallback<Repository> CALLBACK = new DiffUtil.ItemCallback<Repository>() {
        @Override
        public boolean areItemsTheSame(Repository oldItem, Repository newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(Repository oldItem, Repository newItem) {
            return true;
        }
    };
}
