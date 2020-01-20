package com.example.desafio_concrete.networkUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("stargazers_count")
    @Expose
    private String numberStars;

    @SerializedName("forks_count")
    @Expose
    private String numberForks;

    @SerializedName("owner")
    @Expose
    private Owner owner;


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
        description = description;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(String numberStars) {
        this.numberStars = numberStars;
    }

    public String getNumberForks() {
        return numberForks;
    }

    public void setNumberForks(String numberForks) {
        this.numberForks = numberForks;
    }


    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name:'" + name + '\'' +
                ", description:'" + description + '\'' +
                ", full_name:'" + fullName + '\'' +
                ", forks_count:'" + numberForks + '\'' +
                ", stargazers_count:'" + numberStars + '\'' +
                ", owner:'" + owner.toString() + '\'' +
                '}';
    }
}
