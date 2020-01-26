
package br.com.victoramaral.githubdive.model.pojos.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _links {

    @Expose
    private Comments comments;
    @Expose
    private Commits commits;
    @Expose
    private Html html;
    @Expose
    private Issue issue;
    @SerializedName("review_comment")
    private ReviewComment reviewComment;
    @SerializedName("review_comments")
    private ReviewComments reviewComments;
    @Expose
    private Self self;
    @Expose
    private Statuses statuses;

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Commits getCommits() {
        return commits;
    }

    public void setCommits(Commits commits) {
        this.commits = commits;
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

    public ReviewComment getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(ReviewComment reviewComment) {
        this.reviewComment = reviewComment;
    }

    public ReviewComments getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(ReviewComments reviewComments) {
        this.reviewComments = reviewComments;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Statuses getStatuses() {
        return statuses;
    }

    public void setStatuses(Statuses statuses) {
        this.statuses = statuses;
    }

}
