package com.example.desafioconcrete.Objects;

import java.security.acl.Owner;

public class DetailsRepository {

    private String name;
    private String full_name;
    private String description;
    private int forks_count;
    private int stargazers_count;
    private OwnerRepository owner;

    public DetailsRepository(String name, String full_name, String description ,int forks_count, int stargazers_count, OwnerRepository owner) {
        this.name = name;
        this.full_name = full_name;
        this.description = description;
        this.forks_count = forks_count;
        this.stargazers_count = stargazers_count;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public OwnerRepository getOwner() {
        return owner;
    }

    public void setOwner(OwnerRepository owner) {
        this.owner = owner;
    }
}
