package cl.getapps.githubjavarepos.features.repopullrequests.domain.repository


import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.PullRequestsResponse
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import io.reactivex.Single

interface PullRequestsRepository {
    fun fetchPullRequests(params: PullRequestParams): Single<PullRequestsResponse>
}
