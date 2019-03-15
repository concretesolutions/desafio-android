package cl.getapps.githubjavarepos.features.repopullrequests.data.cache

import cl.getapps.githubjavarepos.features.repopullrequests.data.datasource.CacheDataSource
import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.PullRequestsResponse
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import io.reactivex.Completable
import io.reactivex.Single

class PullRequestCacheDataSource : CacheDataSource {
    override fun <T> save(items: T): Completable {
        return Completable.complete()
    }

    override fun clear(): Completable {
        TODO("not implemented")
    }

    override fun getPullRequests(params: PullRequestParams): Single<PullRequestsResponse> {
        TODO("not implemented")
    }

    override fun isCached(): Single<Boolean> {
        return Single.just(false)
    }

    override fun setLastCacheTime(lastCache: Long) {
        TODO("not implemented")
    }

    override fun isExpired(): Boolean {
        TODO("not implemented")
    }
}
