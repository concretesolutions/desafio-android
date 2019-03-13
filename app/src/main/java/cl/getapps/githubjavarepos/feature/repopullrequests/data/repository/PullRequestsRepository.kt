package cl.getapps.githubjavarepos.feature.repopullrequests.data.repository

import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequests
import cl.getapps.githubjavarepos.feature.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.feature.repopullrequests.data.remote.PullRequestsRemote
import cl.getapps.githubjavarepos.feature.repopullrequests.data.source.DataSourceFactory
import cl.getapps.githubjavarepos.feature.repopullrequests.domain.repository.PullRequestsRepository
import io.reactivex.Completable
import io.reactivex.Flowable


class PullRequestsRepository(private val dataStoreFactory: DataSourceFactory) : PullRequestsRepository {

    override fun fetchPullRequests(params: PullRequestParams): Flowable<PullRequests> {
        return dataStoreFactory.retrieveCacheDataSource().isCached()
            .flatMapPublisher {
                dataStoreFactory.retrieveDataSource<PullRequestsRemote>(it).fetchPullRequests(params)
            }
            .flatMap {
                save(it).toSingle { it }.toFlowable()
            }

    }

    fun save(items: PullRequests): Completable {
        return dataStoreFactory.retrieveCacheDataSource().save(items)
    }

    fun clear(): Completable {
        return dataStoreFactory.retrieveCacheDataSource().clear()
    }
}
