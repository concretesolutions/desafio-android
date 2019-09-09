package com.danielmaia.desafio_android.model;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class Repo extends SugarRecord {

    private long guid;
    private String name;
    private long owner_id;
    private String description;
    private long stargazers_count;
    private long forks;

    public Repo() {
    }

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(long stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public long getForks() {
        return forks;
    }

    public void setForks(long forks) {
        this.forks = forks;
    }

    public static List<Repo> findAll() {
        return Select.from(Repo.class).list();
    }

    public static Repo findByGuid(long id) {
        return Select.from(Repo.class).where(Condition.prop("guid").eq(id)).first();
    }
}
