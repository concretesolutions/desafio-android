package cl.getapps.githubjavarepos.feature.repopullrequests.data.remote

import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequest
import io.reactivex.Flowable


class PullRequestsRemote(val pullRequestAPI: PullRequestAPI) {

    fun fetchPullRequest(owner: String, repository: String): Flowable<List<PullRequest>> =
        pullRequestAPI.fetchPullRequests(owner, repository)
}
