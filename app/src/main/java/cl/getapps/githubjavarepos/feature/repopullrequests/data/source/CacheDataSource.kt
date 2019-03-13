package cl.getapps.githubjavarepos.feature.repopullrequests.data.source

import cl.getapps.githubjavarepos.core.data.CacheSource
import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequests
import cl.getapps.githubjavarepos.feature.repopullrequests.data.remote.PullRequestParams
import io.reactivex.Flowable

interface CacheDataSource: CacheSource {
    fun getPullRequests(params: PullRequestParams) : Flowable<PullRequests>
}
