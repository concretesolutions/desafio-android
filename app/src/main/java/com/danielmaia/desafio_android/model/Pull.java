package com.danielmaia.desafio_android.model;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class Pull extends SugarRecord {

    private long guid;
    private String title;
    private long owner_id;
    private String body;
    private PullState state;
    private String url;

    public Pull() {
    }

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getBody() {
        return body == null ? "" : body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public PullState getState() {
        return state;
    }

    public void setState(PullState state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static List<Pull> findAll() {
        return Select.from(Pull.class).list();
    }

    public static Pull findByGuid(long id) {
        return Select.from(Pull.class).where(Condition.prop("guid").eq(id)).first();
    }

    public static long getOpenedCount() {
        return Select.from(Pull.class).where(Condition.prop("state").eq(PullState.open)).list().size();
    }

    public static long getClosedCount() {
        return Select.from(Pull.class).where(Condition.prop("state").eq(PullState.close)).list().size();
    }
}
