package br.com.concrete.desafio_android.domain;

import java.io.Serializable;

/**
 * Created by Daivid
 * To ilustrate how the Gson works
 */
public class Owner implements Serializable {

    private String login = new String();
    private int id = 0;
    private String node_id = new String();
    private String repos_url = new String();
    private String avatar_url = new String();

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

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

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }
}
