package cl.getapps.githubjavarepos.features.repos.data.cache


import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.PullRequestsResponse
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.features.repos.data.entity.ReposResponse
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.features.repos.data.source.CacheDataSource
import io.reactivex.Completable
import io.reactivex.Single

class ReposCacheDataSource : CacheDataSource {
    override fun getRepos(params: ReposParams): Single<ReposResponse> {
        TODO("not implemented")
    }

    override fun <T> save(items: T): Completable {
        return Completable.complete()
    }

    override fun clear(): Completable {
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
