package com.example.desafio_android.pojo.pullrequests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PullRequest {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("node_id")
    @Expose
    var nodeId: String? = null
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String? = null
    @SerializedName("diff_url")
    @Expose
    var diffUrl: String? = null
    @SerializedName("patch_url")
    @Expose
    var patchUrl: String? = null
    @SerializedName("issue_url")
    @Expose
    var issueUrl: String? = null
    @SerializedName("number")
    @Expose
    var number: Int? = null
    @SerializedName("state")
    @Expose
    var state: String? = null
    @SerializedName("locked")
    @Expose
    var locked: Boolean? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("body")
    @Expose
    var body: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("closed_at")
    @Expose
    var closedAt: Any? = null
    @SerializedName("merged_at")
    @Expose
    var mergedAt: Any? = null
    @SerializedName("merge_commit_sha")
    @Expose
    var mergeCommitSha: String? = null
    @SerializedName("assignee")
    @Expose
    var assignee: Any? = null
    @SerializedName("assignees")
    @Expose
    var assignees: List<Any>? = null
    @SerializedName("requested_reviewers")
    @Expose
    var requestedReviewers: List<Any>? = null
    @SerializedName("requested_teams")
    @Expose
    var requestedTeams: List<Any>? = null
    @SerializedName("labels")
    @Expose
    var labels: List<Any>? = null
    @SerializedName("milestone")
    @Expose
    var milestone: Any? = null
    @SerializedName("commits_url")
    @Expose
    var commitsUrl: String? = null
    @SerializedName("review_comments_url")
    @Expose
    var reviewCommentsUrl: String? = null
    @SerializedName("review_comment_url")
    @Expose
    var reviewCommentUrl: String? = null
    @SerializedName("comments_url")
    @Expose
    var commentsUrl: String? = null
    @SerializedName("statuses_url")
    @Expose
    var statusesUrl: String? = null
    @SerializedName("head")
    @Expose
    var head: Head? = null
    @SerializedName("base")
    @Expose
    var base: Base? = null
    @SerializedName("_links")
    @Expose
    var links: Links? = null
    @SerializedName("author_association")
    @Expose
    var authorAssociation: String? = null

}
