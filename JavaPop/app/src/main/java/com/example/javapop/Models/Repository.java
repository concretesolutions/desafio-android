package com.example.javapop.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Repository implements Parcelable {

    @SerializedName("name")
    private String nameRepository;

    @SerializedName("description")
    private String descriptionRepository;

    @SerializedName("owner")
    private OwnerGit ownerGit;

    @SerializedName("full_name")
    private String lastName;

    @SerializedName("stargazers_count")
    private int starsNumber;

    @SerializedName("forks_count")
    private int forkNumber;

    public OwnerGit getOwnerGit() {
        return ownerGit;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNameRepository() {
        return nameRepository;
    }

    public String getDescriptionRepository() {
        return descriptionRepository;
    }

    public int getStarsNumber() {
        return starsNumber;
    }

    public int getForkNumber() {
        return forkNumber;
    }

    protected Repository(Parcel in) {
        this.ownerGit = in.readParcelable(OwnerGit.class.getClassLoader());
        nameRepository = in.readString();
        descriptionRepository = in.readString();
        starsNumber = in.readInt();
        forkNumber = in.readInt();
    }


    @Override
    public String toString() {
        return "Repository{" +
                "nameRepository='" + nameRepository + '\'' +
                ", descriptionRepository='" + descriptionRepository + '\'' +
                ", ownerGit=" + ownerGit +
                ", lastName='" + lastName + '\'' +
                ", starsNumber=" + starsNumber +
                ", forkNumber=" + forkNumber +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(ownerGit, flags);
        dest.writeString(nameRepository);
        dest.writeString(descriptionRepository);
        dest.writeInt(starsNumber);
        dest.writeInt(forkNumber);
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

}
