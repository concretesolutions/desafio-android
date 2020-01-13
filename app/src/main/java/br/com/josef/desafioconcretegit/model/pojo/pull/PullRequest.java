
package br.com.josef.desafioconcretegit.model.pojo.pull;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PullRequest implements Parcelable {

    @Expose
    private br.com.josef.desafioconcretegit.model.pojo.pull._links _links;
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
    @SerializedName("closed_at")
    private Object closedAt;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("commits_url")
    private String commitsUrl;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("diff_url")
    private String diffUrl;
    @Expose
    private Head head;
    @Expose
    private Repo repos;
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
    @SerializedName("merge_commit_sha")
    private String mergeCommitSha;
    @SerializedName("merged_at")
    private Object mergedAt;
    @Expose
    private Object milestone;
    @SerializedName("node_id")
    private String nodeId;
    @Expose
    private Long number;
    @SerializedName("patch_url")
    private String patchUrl;
    @SerializedName("requested_reviewers")
    private List<Object> requestedReviewers;
    @SerializedName("requested_teams")
    private List<Object> requestedTeams;
    @SerializedName("review_comment_url")
    private String reviewCommentUrl;
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



    protected PullRequest(Parcel in) {
        authorAssociation = in.readString();
        body = in.readString();
        commentsUrl = in.readString();
        commitsUrl = in.readString();
        createdAt = in.readString();
        diffUrl = in.readString();
        htmlUrl = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        issueUrl = in.readString();
        byte tmpLocked = in.readByte();
        locked = tmpLocked == 0 ? null : tmpLocked == 1;
        mergeCommitSha = in.readString();
        nodeId = in.readString();
        if (in.readByte() == 0) {
            number = null;
        } else {
            number = in.readLong();
        }
        patchUrl = in.readString();
        reviewCommentUrl = in.readString();
        reviewCommentsUrl = in.readString();
        state = in.readString();
        statusesUrl = in.readString();
        title = in.readString();
        updatedAt = in.readString();
        url = in.readString();
        repos = in.readParcelable(Repo.class.getClassLoader());
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

    public br.com.josef.desafioconcretegit.model.pojo.pull._links get_links() {
        return _links;
    }


    public void set_links(br.com.josef.desafioconcretegit.model.pojo.pull._links _links) {
        this._links = _links;
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

    public PullRequest(String title){
        this.title = title;
    }

    public PullRequest(br.com.josef.desafioconcretegit.model.pojo.pull._links _links, Object assignee, List<Object> assignees, String authorAssociation, Base base, String body, Object closedAt, String commentsUrl, String commitsUrl, String createdAt, String diffUrl, Head head, Repo repos, String htmlUrl, Long id, String issueUrl, List<Object> labels, Boolean locked, String mergeCommitSha, Object mergedAt, Object milestone, String nodeId, Long number, String patchUrl, List<Object> requestedReviewers, List<Object> requestedTeams, String reviewCommentUrl, String reviewCommentsUrl, String state, String statusesUrl, String title, String updatedAt, String url, User user) {
        this._links = _links;
        this.assignee = assignee;
        this.assignees = assignees;
        this.authorAssociation = authorAssociation;
        this.base = base;
        this.body = body;
        this.closedAt = closedAt;
        this.commentsUrl = commentsUrl;
        this.commitsUrl = commitsUrl;
        this.createdAt = createdAt;
        this.diffUrl = diffUrl;
        this.head = head;
        this.repos = repos;
        this.htmlUrl = htmlUrl;
        this.id = id;
        this.issueUrl = issueUrl;
        this.labels = labels;
        this.locked = locked;
        this.mergeCommitSha = mergeCommitSha;
        this.mergedAt = mergedAt;
        this.milestone = milestone;
        this.nodeId = nodeId;
        this.number = number;
        this.patchUrl = patchUrl;
        this.requestedReviewers = requestedReviewers;
        this.requestedTeams = requestedTeams;
        this.reviewCommentUrl = reviewCommentUrl;
        this.reviewCommentsUrl = reviewCommentsUrl;
        this.state = state;
        this.statusesUrl = statusesUrl;
        this.title = title;
        this.updatedAt = updatedAt;
        this.url = url;
        this.user = user;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Repo getRepos() {
        return repos;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
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

    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    public void setMergeCommitSha(String mergeCommitSha) {
        this.mergeCommitSha = mergeCommitSha;
    }

    public Object getMergedAt() {
        return mergedAt;
    }

    public void setMergedAt(Object mergedAt) {
        this.mergedAt = mergedAt;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(authorAssociation);
        dest.writeString(body);
        dest.writeString(commentsUrl);
        dest.writeString(commitsUrl);
        dest.writeString(createdAt);
        dest.writeString(diffUrl);
        dest.writeString(htmlUrl);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(issueUrl);
        dest.writeByte((byte) (locked == null ? 0 : locked ? 1 : 2));
        dest.writeString(mergeCommitSha);
        dest.writeString(nodeId);
        if (number == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(number);
        }
        dest.writeString(patchUrl);
        dest.writeString(reviewCommentUrl);
        dest.writeString(reviewCommentsUrl);
        dest.writeString(state);
        dest.writeString(statusesUrl);
        dest.writeString(title);
        dest.writeString(updatedAt);
        dest.writeString(url);
        dest.writeParcelable(repos, flags);
    }
}
