package cl.getapps.githubjavarepos.feature.repopullrequests.data.source


import cl.getapps.githubjavarepos.core.data.RemoteSource
import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequests
import cl.getapps.githubjavarepos.feature.repopullrequests.data.remote.PullRequestParams
import io.reactivex.Flowable

interface RemoteDataSource : RemoteSource {
    fun fetchPullRequests(params: PullRequestParams): Flowable<PullRequests>
}
