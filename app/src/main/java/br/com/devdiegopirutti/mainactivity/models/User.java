package br.com.devdiegopirutti.mainactivity.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("gists_url")
    private String gistsUrl;

    @SerializedName("repos_url")
    private String reposUrl;

    @SerializedName("following_url")
    private String followingUrl;

    @SerializedName("starred_url")
    private String starredUrl;

    @SerializedName("login")
    private String login;

    @SerializedName("followers_url")
    private String followersUrl;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;

    @SerializedName("received_events_url")
    private String receivedEventsUrl;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("events_url")
    private String eventsUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("site_admin")
    private boolean siteAdmin;

    @SerializedName("id")
    private int id;

    @SerializedName("gravatar_id")
    private String gravatarId;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("organizations_url")
    private String organizationsUrl;

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public int getId() {
        return id;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    @Override
    public String toString() {
        return
                "User{" +
                        "gists_url = '" + gistsUrl + '\'' +
                        ",repos_url = '" + reposUrl + '\'' +
                        ",following_url = '" + followingUrl + '\'' +
                        ",starred_url = '" + starredUrl + '\'' +
                        ",login = '" + login + '\'' +
                        ",followers_url = '" + followersUrl + '\'' +
                        ",type = '" + type + '\'' +
                        ",url = '" + url + '\'' +
                        ",subscriptions_url = '" + subscriptionsUrl + '\'' +
                        ",received_events_url = '" + receivedEventsUrl + '\'' +
                        ",avatar_url = '" + avatarUrl + '\'' +
                        ",events_url = '" + eventsUrl + '\'' +
                        ",html_url = '" + htmlUrl + '\'' +
                        ",site_admin = '" + siteAdmin + '\'' +
                        ",id = '" + id + '\'' +
                        ",gravatar_id = '" + gravatarId + '\'' +
                        ",node_id = '" + nodeId + '\'' +
                        ",organizations_url = '" + organizationsUrl + '\'' +
                        "}";
    }
}