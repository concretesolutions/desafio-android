package com.danielmaia.desafioandroid.service.responses.dto;

public class RepoDto {
    private long id;
    private String name;
    private OwnerDto owner;
    private String description;
    private long stargazers_count;
    private long forks;

    public RepoDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OwnerDto getOwner() {
        return owner;
    }

    public void setOwner(OwnerDto owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(long stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public long getForks() {
        return forks;
    }

    public void setForks(long forks) {
        this.forks = forks;
    }
}
