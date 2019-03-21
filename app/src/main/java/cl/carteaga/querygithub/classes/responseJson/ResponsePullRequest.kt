package cl.carteaga.querygithub.classes.responseJson

import com.google.gson.annotations.SerializedName

data class ResponsePullRequest(

	@field:SerializedName("issue_url")
	val issueUrl: String? = null,

	@field:SerializedName("_links")
	val links: Links? = null,

	@field:SerializedName("diff_url")
	val diffUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("assignees")
	val assignees: List<Any?>? = null,

	@field:SerializedName("requested_reviewers")
	val requestedReviewers: List<Any?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("requested_teams")
	val requestedTeams: List<Any?>? = null,

	@field:SerializedName("head")
	val head: Head? = null,

	@field:SerializedName("author_association")
	val authorAssociation: String? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("patch_url")
	val patchUrl: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("merge_commit_sha")
	val mergeCommitSha: String? = null,

	@field:SerializedName("comments_url")
	val commentsUrl: String? = null,

	@field:SerializedName("review_comment_url")
	val reviewCommentUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("locked")
	val locked: Boolean? = null,

	@field:SerializedName("commits_url")
	val commitsUrl: String? = null,

	@field:SerializedName("closed_at")
	val closedAt: Any? = null,

	@field:SerializedName("statuses_url")
	val statusesUrl: String? = null,

	@field:SerializedName("merged_at")
	val mergedAt: Any? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("labels")
	val labels: List<Any?>? = null,

	@field:SerializedName("milestone")
	val milestone: Any? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("review_comments_url")
	val reviewCommentsUrl: String? = null,

	@field:SerializedName("assignee")
	val assignee: Any? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null,

	@field:SerializedName("base")
	val base: Base? = null
)