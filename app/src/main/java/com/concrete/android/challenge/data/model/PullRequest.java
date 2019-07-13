package com.concrete.android.challenge.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;

/**
 * @author Thiago Corredo
 * @since 2019-07-11
 */
public class PullRequest implements Parcelable {

  public static final Creator<PullRequest> CREATOR = new Creator<PullRequest>() {
    @Override public PullRequest createFromParcel(Parcel source) {
      return new PullRequest(source);
    }

    @Override public PullRequest[] newArray(int size) {
      return new PullRequest[size];
    }
  };

  @Json(name = "title") private String title;
  @Json(name = "created_at") private String createDate;
  @Json(name = "body") private String body;
  @Json(name = "state") private String state;
  @Json(name = "html_url") private String htmlUrl;
  @Json(name = "user") private Owner user;

  public PullRequest() {
  }

  public PullRequest(String title, String createDate, String body, String state,
      String htmlUrl, Owner user) {
    this.title = title;
    this.createDate = createDate;
    this.body = body;
    this.state = state;
    this.htmlUrl = htmlUrl;
    this.user = user;
  }

  protected PullRequest(Parcel in) {
    this.title = in.readString();
    this.createDate = in.readString();
    this.body = in.readString();
    this.state = in.readString();
    this.htmlUrl = in.readString();
    this.user = in.readParcelable(Owner.class.getClassLoader());
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public Owner getUser() {
    return user;
  }

  public void setUser(Owner user) {
    this.user = user;
  }

  @Override public String toString() {
    return "PullRequest{" +
        "title='" + title + '\'' +
        ", createDate='" + createDate + '\'' +
        ", body='" + body + '\'' +
        ", state='" + state + '\'' +
        ", htmlUrl='" + htmlUrl + '\'' +
        ", user=" + user +
        '}';
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.title);
    dest.writeString(this.createDate);
    dest.writeString(this.body);
    dest.writeString(this.state);
    dest.writeString(this.htmlUrl);
    dest.writeParcelable(this.user, flags);
  }

  @Override public int describeContents() {
    return 0;
  }
}
