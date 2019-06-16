package br.com.concrete.desafio.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Repository implements Parcelable {
    private String name;
    private String description;
    private User owner;
    private int stargazers_count;
    private int forks_count;
    private String pulls_url;

    private Repository(Parcel in) {
        name = in.readString();
        description = in.readString();
        owner = in.readParcelable(User.class.getClassLoader());
        stargazers_count = in.readInt();
        forks_count = in.readInt();
        pulls_url = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public String getPulls_url() {
        return pulls_url;
    }

    public void setPulls_url(String pulls_url) {
        this.pulls_url = pulls_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeParcelable(owner, 0);
        dest.writeInt(stargazers_count);
        dest.writeInt(forks_count);
        dest.writeString(pulls_url);
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
