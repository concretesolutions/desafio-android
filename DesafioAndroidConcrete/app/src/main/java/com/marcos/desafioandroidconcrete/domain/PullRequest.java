package com.marcos.desafioandroidconcrete.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marco on 17,Abril,2020
 */
public class PullRequest implements Parcelable {
    private String title;
    private Owner user;
    private String body;
    @SerializedName("created_at")
    private String created;
    @SerializedName("html_url")
    private String url;

    protected PullRequest(Parcel in) {
        title = in.readString();
        body = in.readString();
        created = in.readString();
        url = in.readString();
    }

    public static final Creator<PullRequest> CREATOR = new Creator<PullRequest>() {
        @Override
        public PullRequest createFromParcel(Parcel in) {
            return new PullRequest(in);
        }

        @Override
        public PullRequest[] newArray(int size) {
            return new PullRequest[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Owner getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public String getCreated() {
        return created;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(created);
        dest.writeString(url);
    }
}
