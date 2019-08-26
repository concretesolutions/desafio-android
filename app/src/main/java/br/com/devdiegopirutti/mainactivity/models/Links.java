package br.com.devdiegopirutti.mainactivity.models;

import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("comments")
    private Comments comments;

    @SerializedName("issue")
    private Issue issue;

    @SerializedName("self")
    private Self self;

    @SerializedName("review_comments")
    private ReviewComments reviewComments;

    @SerializedName("commits")
    private Commits commits;

    @SerializedName("statuses")
    private Statuses statuses;

    @SerializedName("html")
    private Html html;

    @SerializedName("review_comment")
    private ReviewComment reviewComment;

    public Comments getComments() {
        return comments;
    }

    public Issue getIssue() {
        return issue;
    }

    public Self getSelf() {
        return self;
    }

    public ReviewComments getReviewComments() {
        return reviewComments;
    }

    public Commits getCommits() {
        return commits;
    }

    public Statuses getStatuses() {
        return statuses;
    }

    public Html getHtml() {
        return html;
    }

    public ReviewComment getReviewComment() {
        return reviewComment;
    }

    @Override
    public String toString() {
        return
                "Links{" +
                        "comments = '" + comments + '\'' +
                        ",issue = '" + issue + '\'' +
                        ",self = '" + self + '\'' +
                        ",review_comments = '" + reviewComments + '\'' +
                        ",commits = '" + commits + '\'' +
                        ",statuses = '" + statuses + '\'' +
                        ",html = '" + html + '\'' +
                        ",review_comment = '" + reviewComment + '\'' +
                        "}";
    }
}