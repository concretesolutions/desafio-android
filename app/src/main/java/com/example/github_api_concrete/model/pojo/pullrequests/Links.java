package com.example.github_api_concrete.model.pojo.pullrequests;

import com.google.gson.annotations.SerializedName;

public class Links{

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

	public void setComments(Comments comments){
		this.comments = comments;
	}

	public Comments getComments(){
		return comments;
	}

	public void setIssue(Issue issue){
		this.issue = issue;
	}

	public Issue getIssue(){
		return issue;
	}

	public void setSelf(Self self){
		this.self = self;
	}

	public Self getSelf(){
		return self;
	}

	public void setReviewComments(ReviewComments reviewComments){
		this.reviewComments = reviewComments;
	}

	public ReviewComments getReviewComments(){
		return reviewComments;
	}

	public void setCommits(Commits commits){
		this.commits = commits;
	}

	public Commits getCommits(){
		return commits;
	}

	public void setStatuses(Statuses statuses){
		this.statuses = statuses;
	}

	public Statuses getStatuses(){
		return statuses;
	}

	public void setHtml(Html html){
		this.html = html;
	}

	public Html getHtml(){
		return html;
	}

	public void setReviewComment(ReviewComment reviewComment){
		this.reviewComment = reviewComment;
	}

	public ReviewComment getReviewComment(){
		return reviewComment;
	}

	@Override
 	public String toString(){
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