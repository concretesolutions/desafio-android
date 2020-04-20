package com.marcos.desafioandroidconcrete.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by marco on 16,Abril,2020
 */
public class RepositoryDetail implements Serializable, Parcelable {
    private int id;
    private Owner owner;
    private String name;
    private String description;
    private int forks;
    private int stargazers_count;

    protected RepositoryDetail(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        forks = in.readInt();
        stargazers_count = in.readInt();
    }

    public static final Creator<RepositoryDetail> CREATOR = new Creator<RepositoryDetail>() {
        @Override
        public RepositoryDetail createFromParcel(Parcel in) {
            return new RepositoryDetail(in);
        }

        @Override
        public RepositoryDetail[] newArray(int size) {
            return new RepositoryDetail[size];
        }
    };

    public int getId() {
        return id;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getForks() {
        return forks;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(forks);
        dest.writeInt(stargazers_count);
    }
}
