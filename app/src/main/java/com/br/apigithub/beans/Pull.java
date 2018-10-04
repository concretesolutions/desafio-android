package com.br.apigithub.beans;

/**
 * Created by rlima on 04/10/18.
 */

public class Pull {
    private Integer number;
    private String title;
    private String body;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

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
}
