package com.example.gitbusca.model;

public class Git {
    private long id;
    private String name;
    private String description;
    private String login;
    private String full_name;
    private int stargazers_count;
    private int forks_count;
    private Owner owner;

    public Git(long id, String name, String description, String login, String full_name, int stargazers_count, int forks_count, Owner owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.login = login;
        this.full_name = full_name;
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;
        this.owner = owner;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
