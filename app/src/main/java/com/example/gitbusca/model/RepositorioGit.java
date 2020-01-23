package com.example.gitbusca.model;

import java.io.Serializable;

public class RepositorioGit implements Serializable {

    private long idRepositorio;
    private String name;
    private String description;
    private String login;
    private String full_name;
    private int stargazers_count;
    private int forks_count;
    private String avatar_url;

    public RepositorioGit(long idRepositorio, String name, String description, String login,
                          String full_name, int stargazers_count, int forks_count) {
        this.idRepositorio = idRepositorio;
        this.name = name;
        this.description = description;
        this.login = login;
        this.full_name = full_name;
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;
    }

    public RepositorioGit(long idRepositorio, String name, String description, String login,
                          String full_name, int stargazers_count, int forks_count, String avatar_url) {
        this.idRepositorio = idRepositorio;
        this.name = name;
        this.description = description;
        this.login = login;
        this.full_name = full_name;
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;
        this.avatar_url = avatar_url;
    }

    public long getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(long idRepositorio) {
        this.idRepositorio = idRepositorio;
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

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
