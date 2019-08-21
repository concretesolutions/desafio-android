package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.data

import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.response.PullRequest
import java.text.SimpleDateFormat
import java.util.*

data class PullRequest(
    val url: String,
    val id: Int,
    val title: String,
    val description: String,
    private val createdAt: String,
    val user: User
) {
    constructor(pullRequest: PullRequest) : this(
        url = pullRequest.url,
        id = pullRequest.id,
        title = pullRequest.title,
        description = pullRequest.description.orEmpty(),
        createdAt = pullRequest.createdAt,
        user = User(pullRequest.user.login, pullRequest.user.avatarUrl)
    )

    fun creationDate(): String {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val stringFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return stringFormatter.format(requireNotNull(dateFormatter.parse(createdAt)))
    }
}