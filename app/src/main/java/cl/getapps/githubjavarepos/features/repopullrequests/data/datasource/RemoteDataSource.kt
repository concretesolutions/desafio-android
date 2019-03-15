package cl.getapps.githubjavarepos.features.repopullrequests.data.datasource


import cl.getapps.githubjavarepos.core.data.RemoteSource
import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.PullRequestsResponse
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import io.reactivex.Single

interface RemoteDataSource : RemoteSource {
    fun fetchPullRequests(params: PullRequestParams): Single<PullRequestsResponse>
}
