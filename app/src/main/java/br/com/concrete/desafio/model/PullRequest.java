package br.com.concrete.desafio.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PullRequest implements Parcelable {
    private String title;
    private String body;
    private User user;
    private String created_at;
    private String html_url;

    private PullRequest(Parcel in) {
        title = in.readString();
        body = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        created_at = in.readString();
        html_url = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(body);
        dest.writeParcelable(user, 0);
        dest.writeString(created_at);
        dest.writeString(html_url);
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
}
