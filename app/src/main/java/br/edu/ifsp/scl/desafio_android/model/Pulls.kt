package br.edu.ifsp.scl.desafio_android.model

import com.google.gson.annotations.SerializedName

data class Pulls(
    @SerializedName("pull") val items: LinkedHashSet<Pull?>? = linkedSetOf()
)

data class Pull(
    @SerializedName("url") var url: String,
    @SerializedName("id") var id: Long,
    @SerializedName("node_id") var node_id: String,
    @SerializedName("html_url") var html_url: String,
    @SerializedName("diff_url") var diff_url: String,
    @SerializedName("patch_url") var patch_url: String,
    @SerializedName("issue_url") var issue_url: String,
    @SerializedName("number") var number: Int,
    @SerializedName("state") var state: String,
    @SerializedName("locked") var locked: Boolean,
    @SerializedName("title") var title: String,
    @SerializedName("user") var user: Owner,    // object Owner
    @SerializedName("body") var body: String,
    @SerializedName("created_at") var created_at: String,
    @SerializedName("updated_at") var updated_at: String,
    @SerializedName("closed_at") var closed_at: String,
    @SerializedName("merged_at") var merged_at: String,
    @SerializedName("merge_commit_sha") var merge_commit_sha: String,
    //@SerializedName("assignee") var assignee: String,// object
    //@SerializedName("assignees") var assignees: LinkedHashSet<String?>? = linkedSetOf(),
    //@SerializedName("requested_reviewers") var requested_reviewers: LinkedHashSet<String?>? = linkedSetOf(),
    //@SerializedName("requested_teams") var requested_teams: LinkedHashSet<String?>? = linkedSetOf(),
    //@SerializedName("labels") var labels: LinkedHashSet<String?>? = linkedSetOf(),
    //@SerializedName("milestone") var milestone: String,    // object
    @SerializedName("commits_url") var commits_url: String,
    @SerializedName("review_comments_url") var review_comments_url: String,
    @SerializedName("review_comment_url") var review_comment_url: String,
    @SerializedName("comments_url") var comments_url: String,
    @SerializedName("statuses_url") var statuses_url: String,
    @SerializedName("head") var head: Head_Base,
    @SerializedName("base") var base: Head_Base
)

data class Head_Base(
    @SerializedName("label") var label: String,
    @SerializedName("ref") var ref: String,
    @SerializedName("sha") var sha: String,
    @SerializedName("user") var user: Owner,
    @SerializedName("repo") var repo: Repository
)