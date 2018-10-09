package com.joaoibarra.gitapp.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Pull (
        @SerializedName("id") val id: Int,
        @SerializedName("url") val url: String,
        @SerializedName("node_id") val nodeId: String,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("diff_url") val diffUrl: String,
        @SerializedName("patch_url") val patchUrl: String,
        @SerializedName("issue_url") val issueUrl: String,
        @SerializedName("number") val numberId: Int,
        @SerializedName("state") val state: String,
        @SerializedName("locked") val locked: Boolean,
        @SerializedName("title") val title: String,
        @SerializedName("user") val user: User,
        @SerializedName("body") val body: String,
        @SerializedName("created_at") val createdAt: Date,
        @SerializedName("updated_at") val updatedAt: String,
        @SerializedName("closed_at") val closedAt: String,
        @SerializedName("merged_at") val mergedAt: String,
        @SerializedName("merge_commit_sha") val mergeCommitSha: String,
        @SerializedName("assignee") val assignee: User,
        @SerializedName("assignees") val assignees: List<User>,
        @SerializedName("requested_reviewers") val requestedReviewers: List<User>,
        @SerializedName("requested_teams") val requestedTeams: List<Team>,
        @SerializedName("labels") val labels: List<Label>,
        @SerializedName("milestone") val milestone: String,
        @SerializedName("commits_url") val commitsUrl: String,
        @SerializedName("review_comments_url") val reviewCommentsUrl: String,
        @SerializedName("review_comment_url") val reviewCommentUrl: String,
        @SerializedName("comments_url") val commentsUrl: String,
        @SerializedName("statuses_url") val statusesUrl: String,
        @SerializedName("head") val head: Head,
        @SerializedName("base") val base: Base,
        @SerializedName("_links") val links: Links,
        @SerializedName("author_association") val authorAssociation: String
)