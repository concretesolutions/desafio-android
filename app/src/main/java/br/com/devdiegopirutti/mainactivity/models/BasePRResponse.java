package br.com.devdiegopirutti.mainactivity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasePRResponse {

    @SerializedName("issue_url")
    private String issueUrl;

    @SerializedName("_links")
    private Links links;

    @SerializedName("diff_url")
    private String diffUrl;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("assignees")
    private List<Object> assignees;

    @SerializedName("requested_reviewers")
    private List<Object> requestedReviewers;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("requested_teams")
    private List<Object> requestedTeams;

    @SerializedName("head")
    private Head head;

    @SerializedName("author_association")
    private String authorAssociation;

    @SerializedName("number")
    private int number;

    @SerializedName("patch_url")
    private String patchUrl;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("merge_commit_sha")
    private String mergeCommitSha;

    @SerializedName("comments_url")
    private String commentsUrl;

    @SerializedName("review_comment_url")
    private String reviewCommentUrl;

    @SerializedName("id")
    private int id;

    @SerializedName("state")
    private String state;

    @SerializedName("locked")
    private boolean locked;

    @SerializedName("commits_url")
    private String commitsUrl;

    @SerializedName("closed_at")
    private Object closedAt;

    @SerializedName("statuses_url")
    private String statusesUrl;

    @SerializedName("merged_at")
    private Object mergedAt;

    @SerializedName("url")
    private String url;

    @SerializedName("labels")
    private List<Object> labels;

    @SerializedName("milestone")
    private Object milestone;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("review_comments_url")
    private String reviewCommentsUrl;

    @SerializedName("assignee")
    private Object assignee;

    @SerializedName("user")
    private User user;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("base")
    private Base base;

    public String getIssueUrl() {
        return issueUrl;
    }

    public Links getLinks() {
        return links;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<Object> getAssignees() {
        return assignees;
    }

    public List<Object> getRequestedReviewers() {
        return requestedReviewers;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public List<Object> getRequestedTeams() {
        return requestedTeams;
    }

    public Head getHead() {
        return head;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public int getNumber() {
        return number;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getReviewCommentUrl() {
        return reviewCommentUrl;
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public Object getMergedAt() {
        return mergedAt;
    }

    public String getUrl() {
        return url;
    }

    public List<Object> getLabels() {
        return labels;
    }

    public Object getMilestone() {
        return milestone;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getReviewCommentsUrl() {
        return reviewCommentsUrl;
    }

    public Object getAssignee() {
        return assignee;
    }

    public User getUser() {
        return user;
    }

    public String getNodeId() {
        return nodeId;
    }

    public Base getBase() {
        return base;
    }

    @Override
    public String toString() {
        return
                "BasePRResponse{" +
                        "issue_url = '" + issueUrl + '\'' +
                        ",_links = '" + links + '\'' +
                        ",diff_url = '" + diffUrl + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",assignees = '" + assignees + '\'' +
                        ",requested_reviewers = '" + requestedReviewers + '\'' +
                        ",title = '" + title + '\'' +
                        ",body = '" + body + '\'' +
                        ",requested_teams = '" + requestedTeams + '\'' +
                        ",head = '" + head + '\'' +
                        ",author_association = '" + authorAssociation + '\'' +
                        ",number = '" + number + '\'' +
                        ",patch_url = '" + patchUrl + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",merge_commit_sha = '" + mergeCommitSha + '\'' +
                        ",comments_url = '" + commentsUrl + '\'' +
                        ",review_comment_url = '" + reviewCommentUrl + '\'' +
                        ",id = '" + id + '\'' +
                        ",state = '" + state + '\'' +
                        ",locked = '" + locked + '\'' +
                        ",commits_url = '" + commitsUrl + '\'' +
                        ",closed_at = '" + closedAt + '\'' +
                        ",statuses_url = '" + statusesUrl + '\'' +
                        ",merged_at = '" + mergedAt + '\'' +
                        ",url = '" + url + '\'' +
                        ",labels = '" + labels + '\'' +
                        ",milestone = '" + milestone + '\'' +
                        ",html_url = '" + htmlUrl + '\'' +
                        ",review_comments_url = '" + reviewCommentsUrl + '\'' +
                        ",assignee = '" + assignee + '\'' +
                        ",user = '" + user + '\'' +
                        ",node_id = '" + nodeId + '\'' +
                        ",base = '" + base + '\'' +
                        "}";
    }
}