package br.com.concrete.desafio_android.domain;

import java.io.Serializable;

/**
 * Created by Daivid
 * To ilustrate how the Gson works
 */
public class User implements Serializable {

    private String login;
    private int id;
    private String avatar_url;
    private String url;
    private String html_url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

}
