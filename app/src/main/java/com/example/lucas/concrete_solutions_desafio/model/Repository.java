package com.example.lucas.concrete_solutions_desafio.model;

import com.example.lucas.concrete_solutions_desafio.R;
import com.google.gson.annotations.SerializedName;

public class Repository {

    private String name;
    private String description;
    private String stargazers_count;
    private String forks_count;
    private String full_name;
    private int color_details;

    @SerializedName("owner")
    private User user;

    public Repository(String name, String description, String stargazers_count, String forks_count, String full_name, User user) {
        this.name = name;
        this.description = description;
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;
        this.full_name = full_name;
        this.user = user;
    }

    public Repository() {
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(String stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getForks_count() {
        return forks_count;
    }

    public void setForks_count(String forks_count) {
        this.forks_count = forks_count;
    }

    public int getColorDetails() {
        return color_details;
    }

    public void setColorDetails(int position) {

        if(position<4){
            position=position+4;
        }

        switch(position%4){
            case 0:
                this.color_details = R.color.red;
                break;
            case 1:
                this.color_details = R.color.yellow;
                break;
            case 2:
                this.color_details = R.color.green;
                break;
            case 3:
                this.color_details = R.color.blue;
                break;
        }

    }
}
