package com.jcjm.desafioandroid.model;

public class PullRequest {
    private String id;
    private User user;
    private String title;
    private String body;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PullRequest that = (PullRequest) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return body != null ? body.equals(that.body) : that.body == null;
    }


}
