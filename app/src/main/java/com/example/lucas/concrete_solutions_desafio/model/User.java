package com.example.lucas.concrete_solutions_desafio.model;

public class User {

    private String login;
    private String avatar_url;

    public User(String login, String avatar_url) {
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
