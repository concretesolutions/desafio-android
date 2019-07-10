package com.example.javapop.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OwnerGit implements Parcelable {

    private long id;

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String URLimage;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getURLimage() {
        return URLimage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(URLimage);
        dest.writeString(username);
    }

    protected OwnerGit(Parcel in) {
        id = in.readLong();
        URLimage = in.readString();
        username = in.readString();
    }

    public static final Creator<OwnerGit> CREATOR = new Creator<OwnerGit>() {
        @Override
        public OwnerGit createFromParcel(Parcel in) {
            return new OwnerGit(in);
        }

        @Override
        public OwnerGit[] newArray(int size) {
            return new OwnerGit[size];
        }
    };

    @Override
    public String toString() {
        return "OwnerGit{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", URLimage='" + URLimage + '\'' +
                '}';
    }
}
