package com.marcos.desafioandroidconcrete.domain;

import java.io.Serializable;

/**
 * Created by marco on 17,Abril,2020
 */
public class Owner implements Serializable {
    private int id;
    private String login;
    private String avatar_url;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }
}
