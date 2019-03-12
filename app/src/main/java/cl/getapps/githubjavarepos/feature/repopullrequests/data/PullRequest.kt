package cl.getapps.githubjavarepos.feature.repopullrequests.data

import cl.getapps.githubjavarepos.feature.repopullrequests.domain.PullRequest

data class PullRequest(
    val url: String,
    val id: Int,
    val node_id: String,
    val html_url: String,
    val diff_url: String,
    val patch_url: String,
    val issue_url: String,
    val number: Int,
    val state: String,
    val locked: Boolean,
    val title: String,
    val user: User,
    val body: String,
    val created_at: String,
    val updated_at: String,
    val closed_at: String,
    val merged_at: String,
    val merge_commit_sha: String,
    val assignee: String,
    val assignees: List<String>,
    val requested_reviewers: List<String>,
    val requested_teams: List<String>,
    val labels: List<String>,
    val milestone: String,
    val commits_url: String,
    val review_comments_url: String,
    val review_comment_url: String,
    val comments_url: String,
    val statuses_url: String,
    val head: Head,
    val base: Base,
    val _links: _links,
    val author_association: String
) {
    fun toPullRequest() = PullRequest(user.toUSer(), title, body, created_at)
}