package br.com.bernardino.githubsearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repository (
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("node_id")
    @Expose
    val nodeId: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("full_name")
    @Expose
    val fullName: String,
    @SerializedName("private")
    @Expose
    val private: Boolean,
    @SerializedName("owner")
    @Expose
    val owner: Owner,
    @SerializedName("html_url")
    @Expose
    val htmlUrl: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("fork")
    @Expose
    val fork: Boolean,
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("forks_url")
    @Expose
    val forksUrl: String,
    @SerializedName("keys_url")
    @Expose
    val keysUrl: String,
    @SerializedName("collaborators_url")
    @Expose
    val collaboratorsUrl: String,
    @SerializedName("teams_url")
    @Expose
    val teamsUrl: String,
    @SerializedName("hooks_url")
    @Expose
    val hooksUrl: String,
    @SerializedName("issue_events_url")
    @Expose
    val issueEventsUrl: String,
    @SerializedName("events_url")
    @Expose
    val eventsUrl: String,
    @SerializedName("assignees_url")
    @Expose
    val assigneesUrl: String,
    @SerializedName("branches_url")
    @Expose
    val branchesUrl: String,
    @SerializedName("tags_url")
    @Expose
    val tagsUrl: String,
    @SerializedName("blobs_url")
    @Expose
    val blobsUrl: String,
    @SerializedName("git_tags_url")
    @Expose
    val gitTagsUrl: String,
    @SerializedName("git_refs_url")
    @Expose
    val gitRefsUrl: String,
    @SerializedName("trees_url")
    @Expose
    val treesUrl: String,
    @SerializedName("statuses_url")
    @Expose
    val statusesUrl: String,
    @SerializedName("languages_url")
    @Expose
    val languagesUrl: String,
    @SerializedName("stargazers_url")
    @Expose
    val stargazersUrl: String,
    @SerializedName("contributors_url")
    @Expose
    val contributorsUrl: String,
    @SerializedName("subscribers_url")
    @Expose
    val subscribersUrl: String,
    @SerializedName("subscription_url")
    @Expose
    val subscriptionUrl: String,
    @SerializedName("commits_url")
    @Expose
    val commitsUrl: String,
    @SerializedName("git_commits_url")
    @Expose
    val gitCommitsUrl: String,
    @SerializedName("comments_url")
    @Expose
    val commentsUrl: String,
    @SerializedName("issue_comment_url")
    @Expose
    val issueCommentUrl: String,
    @SerializedName("contents_url")
    @Expose
    val contentsUrl: String,
    @SerializedName("compare_url")
    @Expose
    val compareUrl: String,
    @SerializedName("merges_url")
    @Expose
    val mergesUrl: String,
    @SerializedName("archive_url")
    @Expose
    val archiveUrl: String,
    @SerializedName("downloads_url")
    @Expose
    val downloadsUrl: String,
    @SerializedName("issues_url")
    @Expose
    val issuesUrl: String,
    @SerializedName("pulls_url")
    @Expose
    val pullsUrl: String,
    @SerializedName("milestones_url")
    @Expose
    val milestonesUrl: String,
    @SerializedName("notifications_url")
    @Expose
    val notificationsUrl: String,
    @SerializedName("labels_url")
    @Expose
    val labelsUrl: String,
    @SerializedName("releases_url")
    @Expose
    val releasesUrl: String,
    @SerializedName("deployments_url")
    @Expose
    val deploymentsUrl: String,
    @SerializedName("created_at")
    @Expose
    val createdAt: String,
    @SerializedName("updated_at")
    @Expose
    val updatedAt: String,
    @SerializedName("pushed_at")
    @Expose
    val pushedAt: String,
    @SerializedName("git_url")
    @Expose
    val gitUrl: String,
    @SerializedName("ssh_url")
    @Expose
    val sshUrl: String,
    @SerializedName("clone_url")
    @Expose
    val cloneUrl: String,
    @SerializedName("svn_url")
    @Expose
    val svnUrl: String,
    @SerializedName("homepage")
    @Expose
    val homepage: String,
    @SerializedName("size")
    @Expose
    val size: Int,
    @SerializedName("stargazers_count")
    @Expose
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    @Expose
    val watchersCount: Int,
    @SerializedName("language")
    @Expose
    val language: String,
    @SerializedName("has_issues")
    @Expose
    val hasIssues: Boolean,
    @SerializedName("has_projects")
    @Expose
    val hasProjects: Boolean,
    @SerializedName("has_downloads")
    @Expose
    val hasDownloads: Boolean,
    @SerializedName("has_wiki")
    @Expose
    val hasWiki: Boolean,
    @SerializedName("has_pages")
    @Expose
    val hasPages: Boolean,
    @SerializedName("forks_count")
    @Expose
    val forksCount: Int,
    @SerializedName("mirror_url")
    @Expose
    val mirrorUrl: Any,
    @SerializedName("archived")
    @Expose
    val archived: Boolean,
    @SerializedName("disabled")
    @Expose
    val disabled: Boolean,
    @SerializedName("open_issues_count")
    @Expose
    val openIssuesCount: Int,
    @SerializedName("license")
    @Expose
    val license: Any,
    @SerializedName("forks")
    @Expose
    val forks: Int,
    @SerializedName("open_issues")
    @Expose
    val openIssues: Int,
    @SerializedName("watchers")
    @Expose
    val watchers: Int,
    @SerializedName("default_branch")
    @Expose
    val defaultBranch: String,
    @SerializedName("score")
    @Expose
    val score: Double

)