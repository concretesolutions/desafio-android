package com.concrete.android.challenge.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.squareup.moshi.Json;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class Owner implements Parcelable {

  public static final Creator<Owner> CREATOR = new Creator<Owner>() {
    @Override public Owner createFromParcel(Parcel source) {
      return new Owner(source);
    }

    @Override public Owner[] newArray(int size) {
      return new Owner[size];
    }
  };

  @Json(name = "login") private String login;
  @Json(name = "id") private Long id;
  @Json(name = "node_id") private String nodeId;
  @Json(name = "avatar_url") private String avatarUrl;
  @Json(name = "gravatar_id") private String gravatarId;
  @Json(name = "url") private String url;
  @Json(name = "html_url") private String htmlUrl;
  @Json(name = "followers_url") private String followersUrl;
  @Json(name = "following_url") private String followingUrl;
  @Json(name = "gists_url") private String gistsUrl;
  @Json(name = "starred_url") private String starredUrl;
  @Json(name = "subscriptions_url") private String subscriptionsUrl;
  @Json(name = "organizations_url") private String organizationsUrl;
  @Json(name = "repos_url") private String reposUrl;
  @Json(name = "events_url") private String eventsUrl;
  @Json(name = "received_events_url") private String receivedEventsUrl;
  @Json(name = "type") private String type;
  @Json(name = "site_admin") private Boolean siteAdmin;

  public Owner() {
  }

  public Owner(String login, Long id, String nodeId, String avatarUrl, String gravatarId,
      String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl,
      String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl,
      String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin) {
    this.login = login;
    this.id = id;
    this.nodeId = nodeId;
    this.avatarUrl = avatarUrl;
    this.gravatarId = gravatarId;
    this.url = url;
    this.htmlUrl = htmlUrl;
    this.followersUrl = followersUrl;
    this.followingUrl = followingUrl;
    this.gistsUrl = gistsUrl;
    this.starredUrl = starredUrl;
    this.subscriptionsUrl = subscriptionsUrl;
    this.organizationsUrl = organizationsUrl;
    this.reposUrl = reposUrl;
    this.eventsUrl = eventsUrl;
    this.receivedEventsUrl = receivedEventsUrl;
    this.type = type;
    this.siteAdmin = siteAdmin;
  }

  protected Owner(Parcel in) {
    this.login = in.readString();
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.nodeId = in.readString();
    this.avatarUrl = in.readString();
    this.gravatarId = in.readString();
    this.url = in.readString();
    this.htmlUrl = in.readString();
    this.followersUrl = in.readString();
    this.followingUrl = in.readString();
    this.gistsUrl = in.readString();
    this.starredUrl = in.readString();
    this.subscriptionsUrl = in.readString();
    this.organizationsUrl = in.readString();
    this.reposUrl = in.readString();
    this.eventsUrl = in.readString();
    this.receivedEventsUrl = in.readString();
    this.type = in.readString();
    this.siteAdmin = (Boolean) in.readValue(Boolean.class.getClassLoader());
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getGravatarId() {
    return gravatarId;
  }

  public void setGravatarId(String gravatarId) {
    this.gravatarId = gravatarId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public String getFollowersUrl() {
    return followersUrl;
  }

  public void setFollowersUrl(String followersUrl) {
    this.followersUrl = followersUrl;
  }

  public String getFollowingUrl() {
    return followingUrl;
  }

  public void setFollowingUrl(String followingUrl) {
    this.followingUrl = followingUrl;
  }

  public String getGistsUrl() {
    return gistsUrl;
  }

  public void setGistsUrl(String gistsUrl) {
    this.gistsUrl = gistsUrl;
  }

  public String getStarredUrl() {
    return starredUrl;
  }

  public void setStarredUrl(String starredUrl) {
    this.starredUrl = starredUrl;
  }

  public String getSubscriptionsUrl() {
    return subscriptionsUrl;
  }

  public void setSubscriptionsUrl(String subscriptionsUrl) {
    this.subscriptionsUrl = subscriptionsUrl;
  }

  public String getOrganizationsUrl() {
    return organizationsUrl;
  }

  public void setOrganizationsUrl(String organizationsUrl) {
    this.organizationsUrl = organizationsUrl;
  }

  public String getReposUrl() {
    return reposUrl;
  }

  public void setReposUrl(String reposUrl) {
    this.reposUrl = reposUrl;
  }

  public String getEventsUrl() {
    return eventsUrl;
  }

  public void setEventsUrl(String eventsUrl) {
    this.eventsUrl = eventsUrl;
  }

  public String getReceivedEventsUrl() {
    return receivedEventsUrl;
  }

  public void setReceivedEventsUrl(String receivedEventsUrl) {
    this.receivedEventsUrl = receivedEventsUrl;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Boolean getSiteAdmin() {
    return siteAdmin;
  }

  public void setSiteAdmin(Boolean siteAdmin) {
    this.siteAdmin = siteAdmin;
  }

  @Override public String toString() {
    return "Owner{" +
        "login='" + login + '\'' +
        ", id=" + id +
        ", nodeId='" + nodeId + '\'' +
        ", avatarUrl='" + avatarUrl + '\'' +
        ", gravatarId='" + gravatarId + '\'' +
        ", url='" + url + '\'' +
        ", htmlUrl='" + htmlUrl + '\'' +
        ", followersUrl='" + followersUrl + '\'' +
        ", followingUrl='" + followingUrl + '\'' +
        ", gistsUrl='" + gistsUrl + '\'' +
        ", starredUrl='" + starredUrl + '\'' +
        ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
        ", organizationsUrl='" + organizationsUrl + '\'' +
        ", reposUrl='" + reposUrl + '\'' +
        ", eventsUrl='" + eventsUrl + '\'' +
        ", receivedEventsUrl='" + receivedEventsUrl + '\'' +
        ", type='" + type + '\'' +
        ", siteAdmin=" + siteAdmin +
        '}';
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.login);
    dest.writeValue(this.id);
    dest.writeString(this.nodeId);
    dest.writeString(this.avatarUrl);
    dest.writeString(this.gravatarId);
    dest.writeString(this.url);
    dest.writeString(this.htmlUrl);
    dest.writeString(this.followersUrl);
    dest.writeString(this.followingUrl);
    dest.writeString(this.gistsUrl);
    dest.writeString(this.starredUrl);
    dest.writeString(this.subscriptionsUrl);
    dest.writeString(this.organizationsUrl);
    dest.writeString(this.reposUrl);
    dest.writeString(this.eventsUrl);
    dest.writeString(this.receivedEventsUrl);
    dest.writeString(this.type);
    dest.writeValue(this.siteAdmin);
  }

  @Override public int describeContents() {
    return 0;
  }
}
