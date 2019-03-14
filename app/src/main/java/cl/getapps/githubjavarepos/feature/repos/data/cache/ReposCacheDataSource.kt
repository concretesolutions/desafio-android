package cl.getapps.githubjavarepos.feature.repos.data.cache

import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequests
import cl.getapps.githubjavarepos.feature.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.feature.repopullrequests.data.source.CacheDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class ReposCacheDataSource: CacheDataSource {

    override fun <T> save(items: T): Completable {
        return Completable.complete()
    }

    override fun clear(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPullRequests(params: PullRequestParams): Flowable<PullRequests> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(): Single<Boolean> {
        return Single.just(false)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastCacheTime(lastCache: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
