package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.repository

import dev.theuzfaleiro.trendingongithub.network.GitHubEndpoint
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.data.PullRequest
import io.reactivex.Single

class PullRequestRepository(private val gitHubEndpoint: GitHubEndpoint) {

    fun fetchPullRequests(username: String, repositoryName: String): Single<List<PullRequest>> =
        gitHubEndpoint.fetchPullRequestsFromApi(
            username,
            repositoryName
        ).flatMapIterable { it }
            .map { PullRequest(it) }
            .toList()
}