
package br.com.victoramaral.githubdive.model.pojos.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Request {

    @Expose
    private br.com.victoramaral.githubdive.model.pojos.requests._links _links;
    @Expose
    private Long additions;
    @Expose
    private Object assignee;
    @Expose
    private List<Object> assignees;
    @SerializedName("author_association")
    private String authorAssociation;
    @Expose
    private Base base;
    @Expose
    private String body;
    @SerializedName("changed_files")
    private Long changedFiles;
    @SerializedName("closed_at")
    private String closedAt;
    @Expose
    private Long comments;
    @SerializedName("comments_url")
    private String commentsUrl;
    @Expose
    private Long commits;
    @SerializedName("commits_url")
    private String commitsUrl;
    @SerializedName("created_at")
    private String createdAt;
    @Expose
    private Long deletions;
    @SerializedName("diff_url")
    private String diffUrl;
    @Expose
    private Head head;
    @SerializedName("html_url")
    private String htmlUrl;
    @Expose
    private Long id;
    @SerializedName("issue_url")
    private String issueUrl;
    @Expose
    private List<Object> labels;
    @Expose
    private Boolean locked;
    @SerializedName("maintainer_can_modify")
    private Boolean maintainerCanModify;
    @SerializedName("merge_commit_sha")
    private String mergeCommitSha;
    @Expose
    private Boolean mergeable;
    @SerializedName("mergeable_state")
    private String mergeableState;
    @Expose
    private Boolean merged;
    @SerializedName("merged_at")
    private String mergedAt;
    @SerializedName("merged_by")
    private MergedBy mergedBy;
    @Expose
    private Object milestone;
    @SerializedName("node_id")
    private String nodeId;
    @Expose
    private Long number;
    @SerializedName("patch_url")
    private String patchUrl;
    @Expose
    private Boolean rebaseable;
    @SerializedName("requested_reviewers")
    private List<Object> requestedReviewers;
    @SerializedName("requested_teams")
    private List<Object> requestedTeams;
    @SerializedName("review_comment_url")
    private String reviewCommentUrl;
    @SerializedName("review_comments")
    private Long reviewComments;
    @SerializedName("review_comments_url")
    private String reviewCommentsUrl;
    @Expose
    private String state;
    @SerializedName("statuses_url")
    private String statusesUrl;
    @Expose
    private String title;
    @SerializedName("updated_at")
    private String updatedAt;
    @Expose
    private String url;
    @Expose
    private User user;

    public br.com.victoramaral.githubdive.model.pojos.requests._links get_links() {
        return _links;
    }

    public void set_links(br.com.victoramaral.githubdive.model.pojos.requests._links _links) {
        this._links = _links;
    }

    public Long getAdditions() {
        return additions;
    }

    public void setAdditions(Long additions) {
        this.additions = additions;
    }

    public Object getAssignee() {
        return assignee;
    }

    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    public List<Object> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Object> assignees) {
        this.assignees = assignees;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public void setAuthorAssociation(String authorAssociation) {
        this.authorAssociation = authorAssociation;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getChangedFiles() {
        return changedFiles;
    }

    public void setChangedFiles(Long changedFiles) {
        this.changedFiles = changedFiles;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public Long getCommits() {
        return commits;
    }

    public void setCommits(Long commits) {
        this.commits = commits;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDeletions() {
        return deletions;
    }

    public void setDeletions(Long deletions) {
        this.deletions = deletions;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public void setDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public void setIssueUrl(String issueUrl) {
        this.issueUrl = issueUrl;
    }

    public List<Object> getLabels() {
        return labels;
    }

    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getMaintainerCanModify() {
        return maintainerCanModify;
    }

    public void setMaintainerCanModify(Boolean maintainerCanModify) {
        this.maintainerCanModify = maintainerCanModify;
    }

    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    public void setMergeCommitSha(String mergeCommitSha) {
        this.mergeCommitSha = mergeCommitSha;
    }

    public Boolean getMergeable() {
        return mergeable;
    }

    public void setMergeable(Boolean mergeable) {
        this.mergeable = mergeable;
    }

    public String getMergeableState() {
        return mergeableState;
    }

    public void setMergeableState(String mergeableState) {
        this.mergeableState = mergeableState;
    }

    public Boolean getMerged() {
        return merged;
    }

    public void setMerged(Boolean merged) {
        this.merged = merged;
    }

    public String getMergedAt() {
        return mergedAt;
    }

    public void setMergedAt(String mergedAt) {
        this.mergedAt = mergedAt;
    }

    public MergedBy getMergedBy() {
        return mergedBy;
    }

    public void setMergedBy(MergedBy mergedBy) {
        this.mergedBy = mergedBy;
    }

    public Object getMilestone() {
        return milestone;
    }

    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }

    public Boolean getRebaseable() {
        return rebaseable;
    }

    public void setRebaseable(Boolean rebaseable) {
        this.rebaseable = rebaseable;
    }

    public List<Object> getRequestedReviewers() {
        return requestedReviewers;
    }

    public void setRequestedReviewers(List<Object> requestedReviewers) {
        this.requestedReviewers = requestedReviewers;
    }

    public List<Object> getRequestedTeams() {
        return requestedTeams;
    }

    public void setRequestedTeams(List<Object> requestedTeams) {
        this.requestedTeams = requestedTeams;
    }

    public String getReviewCommentUrl() {
        return reviewCommentUrl;
    }

    public void setReviewCommentUrl(String reviewCommentUrl) {
        this.reviewCommentUrl = reviewCommentUrl;
    }

    public Long getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(Long reviewComments) {
        this.reviewComments = reviewComments;
    }

    public String getReviewCommentsUrl() {
        return reviewCommentsUrl;
    }

    public void setReviewCommentsUrl(String reviewCommentsUrl) {
        this.reviewCommentsUrl = reviewCommentsUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
