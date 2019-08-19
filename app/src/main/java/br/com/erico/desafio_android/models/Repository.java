package br.com.erico.desafio_android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repository implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer repositoryId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("owner")
    @Expose
    private User owner;

    @SerializedName("stargazers_count")
    @Expose
    private Integer starsCount;

    @SerializedName("forks_count")
    @Expose
    private Integer numForks;

    public Repository(Integer repositoryId, String name, String description, User owner, Integer starsCount, Integer numForks) {
        this.repositoryId = repositoryId;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.starsCount = starsCount;
        this.numForks = numForks;
    }


    public Integer getRepositoryId() {

        return repositoryId;
    }

    public void setRepositoryId(Integer repositoryId) {

        this.repositoryId = repositoryId;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(Integer starsCount) {
        this.starsCount = starsCount;
    }

    public Integer getNumForks() {
        return numForks;
    }

    public void setNumForks(Integer numForks) {
        this.numForks = numForks;
    }
}
