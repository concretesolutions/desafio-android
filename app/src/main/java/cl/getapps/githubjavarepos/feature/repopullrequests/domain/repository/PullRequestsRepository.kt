package cl.getapps.githubjavarepos.feature.repopullrequests.domain.repository

import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequests
import cl.getapps.githubjavarepos.feature.repopullrequests.data.remote.PullRequestParams
import io.reactivex.Flowable

interface PullRequestsRepository {
    fun fetchPullRequests(params: PullRequestParams) : Flowable<PullRequests>
}
