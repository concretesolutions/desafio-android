package br.com.concrete.desafio_android.domain;

import java.io.Serializable;

/**
 * Created by Daivid
 * To ilustrate how the Gson works
 */
public class Pull implements Serializable {

    private int id = 0;
    private String html_url = new String();
    private String state = new String();
    private String title = new String();
    private String body = new String();
    private String created_at = new String();
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated_at() {
        String created_at_aux = created_at.split("T")[0];
        return created_at_aux;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
