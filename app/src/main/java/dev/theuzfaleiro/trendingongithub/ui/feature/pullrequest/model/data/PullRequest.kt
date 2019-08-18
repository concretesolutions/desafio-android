package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.data

import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.response.PullRequest

data class PullRequest(
    val url: String,
    val id: Int,
    val title: String,
    val description: String,
    val user: User
) {
    constructor(pullRequest: PullRequest) : this(
        url = pullRequest.url,
        id = pullRequest.id,
        title = pullRequest.title,
        description = pullRequest.description,
        user = User(pullRequest.user.login, pullRequest.user.avatarUrl)
    )
}