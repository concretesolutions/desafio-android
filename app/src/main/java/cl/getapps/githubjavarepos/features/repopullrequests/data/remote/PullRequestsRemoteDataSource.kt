package cl.getapps.githubjavarepos.features.repopullrequests.data.remote

import cl.getapps.githubjavarepos.features.repopullrequests.data.datasource.RemoteDataSource
import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.PullRequestsResponse
import io.reactivex.Single


class PullRequestsRemoteDataSource(val pullRequestAPI: PullRequestAPI) : RemoteDataSource {

    override fun fetchPullRequests(params: PullRequestParams): Single<PullRequestsResponse> {
        return pullRequestAPI.fetchPullRequests(params.owner, params.repository)
    }
}

data class PullRequestParams(val owner: String, val repository: String)
