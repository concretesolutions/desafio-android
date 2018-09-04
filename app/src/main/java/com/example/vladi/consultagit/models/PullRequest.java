package com.example.vladi.consultagit.models;

public class PullRequest {

    private String title;

    private int number;

    private String state;

    private String body;

    private Owner owner;

    public PullRequest(String title, int number, String state, String body, Owner owner) {
        this.title = title;
        this.number = number;
        this.state = state;
        this.body = body;
        this.owner = owner;
    }

    public PullRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
