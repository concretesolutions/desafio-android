package cl.getapps.githubjavarepos.feature.repopullrequests.data.remote

import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequests
import cl.getapps.githubjavarepos.feature.repopullrequests.data.source.RemoteDataSource
import io.reactivex.Flowable


class PullRequestsRemote(val pullRequestAPI: PullRequestAPI): RemoteDataSource {

    override fun fetchPullRequests(params: PullRequestParams): Flowable<PullRequests> {
        return pullRequestAPI.fetchPullRequests(params.owner, params.repository)
    }
}

data class PullRequestParams(val owner: String, val repository: String)
