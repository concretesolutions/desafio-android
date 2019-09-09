package com.danielmaia.desafio_android.model;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class Owner extends SugarRecord {

    private long guid;
    private String login;
    private String avatar_url;

    public Owner() {
    }

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url == null ? "" : avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public static List<Owner> findAll() {
        return Select.from(Owner.class).list();
    }

    public static Owner findByGuid(long id) {
        return Select.from(Owner.class).where(Condition.prop("guid").eq(id)).first();
    }
}
