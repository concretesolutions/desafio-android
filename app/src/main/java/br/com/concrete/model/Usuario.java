package br.com.concrete.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String login;

    public Usuario(){}

    public Usuario(String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNameDefault(){
        return "Concrete";
    }
}