package com.example.sharked.accenture.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repository  implements Serializable {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("forks")
    public String forks;

    @SerializedName("stargazers_count")
    public String stargazersCount;

    @SerializedName("owner")
    public Owner owner;

    @SerializedName("node_id")
    public String nodeId;

    @SerializedName("full_name")
    public String fullName;

    @SerializedName("forks_url")
    public String forksUrl;


    public String getForksUrl() {
        return forksUrl;
    }

    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(String stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
