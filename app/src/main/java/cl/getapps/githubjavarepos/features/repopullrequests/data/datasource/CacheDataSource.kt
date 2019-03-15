package cl.getapps.githubjavarepos.features.repopullrequests.data.datasource

import cl.getapps.githubjavarepos.core.data.CacheSource
import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.PullRequestsResponse
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import io.reactivex.Single

interface CacheDataSource : CacheSource {
    fun getPullRequests(params: PullRequestParams): Single<PullRequestsResponse>
}
