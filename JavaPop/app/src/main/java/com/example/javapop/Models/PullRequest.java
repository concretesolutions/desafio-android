package com.example.javapop.Models;

import com.google.gson.annotations.SerializedName;

public class PullRequest {

    @SerializedName("title")
    private String mTitlePR;

    @SerializedName("created_at")
    private String datePR;

    @SerializedName("user")
    private OwnerGit mOwnerGit;

    @SerializedName("body")
    private String mBody;

    @SerializedName("html_url")
    private String mURL_PR;

    public String getTitlePR() {
        return mTitlePR;
    }

    public String getDatePR() {
        return datePR;
    }

    public OwnerGit getOwnerGit() {
        return mOwnerGit;
    }

    public String getDescriptionPR() {
        return mBody;
    }

    public String getURL_PR() {
        return mURL_PR;
    }

    @Override
    public String toString() {
        return "PullRequest{" +
                "titlePR='" + mTitlePR + '\'' +
                ", datePR='" + datePR + '\'' +
                ", mOwnerGit=" + mOwnerGit +
                ", mBody='" + mBody + '\'' +
                ", mURL_PR='" + mURL_PR + '\'' +
                '}';
    }

}
