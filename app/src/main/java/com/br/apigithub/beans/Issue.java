package com.br.apigithub.beans;

import java.util.List;

/**
 * Created by rlima on 04/10/18.
 */

public class Issue {
    private Integer number;
    private String title;
    private String body;
    private Pull pull_request;

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

    public Pull getPull_request() {
        return pull_request;
    }

    public void setPull_request(Pull pull_request) {
        this.pull_request = pull_request;
    }
}
