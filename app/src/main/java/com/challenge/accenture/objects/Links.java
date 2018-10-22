
package com.challenge.accenture.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("html")
    @Expose
    private Html html;
    @SerializedName("issue")
    @Expose
    private Issue issue;
    @SerializedName("comments")
    @Expose
    private Comments comments;
    @SerializedName("review_comments")
    @Expose
    private ReviewComments reviewComments;
    @SerializedName("review_comment")
    @Expose
    private ReviewComment reviewComment;
    @SerializedName("commits")
    @Expose
    private Commits commits;
    @SerializedName("statuses")
    @Expose
    private Statuses statuses;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Html getHtml() {
        return html;
    }

    public void setHtml(Html html) {
        this.html = html;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public ReviewComments getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(ReviewComments reviewComments) {
        this.reviewComments = reviewComments;
    }

    public ReviewComment getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(ReviewComment reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Commits getCommits() {
        return commits;
    }

    public void setCommits(Commits commits) {
        this.commits = commits;
    }

    public Statuses getStatuses() {
        return statuses;
    }

    public void setStatuses(Statuses statuses) {
        this.statuses = statuses;
    }

}
