package com.example.desafio_concrete.networkUtils;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PullRequest {


    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("user")
    @Expose
    private Owner owner;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


//    @Override
//    public String toString() {
//        return "{" +
//                "title:'" + title + '\'' +
//                ", user:'" + owner.toString() + '\'' +
//                ", body:'" + body + '\'' +
//                "}";
//    }
}
