package br.com.appdesafio.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
@Entity
public class UrlEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    public String url;

    @SerializedName("user_name")
    @ColumnInfo(name = "user_name")
    public String userName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }
}
