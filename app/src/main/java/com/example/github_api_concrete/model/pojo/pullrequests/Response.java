package com.example.github_api_concrete.model.pojo.pullrequests;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{

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

	public void setIssueUrl(String issueUrl){
		this.issueUrl = issueUrl;
	}

	public String getIssueUrl(){
		return issueUrl;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setDiffUrl(String diffUrl){
		this.diffUrl = diffUrl;
	}

	public String getDiffUrl(){
		return diffUrl;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setAssignees(List<Object> assignees){
		this.assignees = assignees;
	}

	public List<Object> getAssignees(){
		return assignees;
	}

	public void setRequestedReviewers(List<Object> requestedReviewers){
		this.requestedReviewers = requestedReviewers;
	}

	public List<Object> getRequestedReviewers(){
		return requestedReviewers;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setRequestedTeams(List<Object> requestedTeams){
		this.requestedTeams = requestedTeams;
	}

	public List<Object> getRequestedTeams(){
		return requestedTeams;
	}

	public void setHead(Head head){
		this.head = head;
	}

	public Head getHead(){
		return head;
	}

	public void setAuthorAssociation(String authorAssociation){
		this.authorAssociation = authorAssociation;
	}

	public String getAuthorAssociation(){
		return authorAssociation;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public int getNumber(){
		return number;
	}

	public void setPatchUrl(String patchUrl){
		this.patchUrl = patchUrl;
	}

	public String getPatchUrl(){
		return patchUrl;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setMergeCommitSha(String mergeCommitSha){
		this.mergeCommitSha = mergeCommitSha;
	}

	public String getMergeCommitSha(){
		return mergeCommitSha;
	}

	public void setCommentsUrl(String commentsUrl){
		this.commentsUrl = commentsUrl;
	}

	public String getCommentsUrl(){
		return commentsUrl;
	}

	public void setReviewCommentUrl(String reviewCommentUrl){
		this.reviewCommentUrl = reviewCommentUrl;
	}

	public String getReviewCommentUrl(){
		return reviewCommentUrl;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setLocked(boolean locked){
		this.locked = locked;
	}

	public boolean isLocked(){
		return locked;
	}

	public void setCommitsUrl(String commitsUrl){
		this.commitsUrl = commitsUrl;
	}

	public String getCommitsUrl(){
		return commitsUrl;
	}

	public void setClosedAt(Object closedAt){
		this.closedAt = closedAt;
	}

	public Object getClosedAt(){
		return closedAt;
	}

	public void setStatusesUrl(String statusesUrl){
		this.statusesUrl = statusesUrl;
	}

	public String getStatusesUrl(){
		return statusesUrl;
	}

	public void setMergedAt(Object mergedAt){
		this.mergedAt = mergedAt;
	}

	public Object getMergedAt(){
		return mergedAt;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setLabels(List<Object> labels){
		this.labels = labels;
	}

	public List<Object> getLabels(){
		return labels;
	}

	public void setMilestone(Object milestone){
		this.milestone = milestone;
	}

	public Object getMilestone(){
		return milestone;
	}

	public void setHtmlUrl(String htmlUrl){
		this.htmlUrl = htmlUrl;
	}

	public String getHtmlUrl(){
		return htmlUrl;
	}

	public void setReviewCommentsUrl(String reviewCommentsUrl){
		this.reviewCommentsUrl = reviewCommentsUrl;
	}

	public String getReviewCommentsUrl(){
		return reviewCommentsUrl;
	}

	public void setAssignee(Object assignee){
		this.assignee = assignee;
	}

	public Object getAssignee(){
		return assignee;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setNodeId(String nodeId){
		this.nodeId = nodeId;
	}

	public String getNodeId(){
		return nodeId;
	}

	public void setBase(Base base){
		this.base = base;
	}

	public Base getBase(){
		return base;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
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