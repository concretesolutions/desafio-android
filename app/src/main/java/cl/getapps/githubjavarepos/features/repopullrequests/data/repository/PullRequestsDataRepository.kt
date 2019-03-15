package cl.getapps.githubjavarepos.features.repopullrequests.data.repository


import cl.getapps.githubjavarepos.features.repopullrequests.data.datasource.PullRequestsDataSourceFactory
import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.PullRequestsResponse
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestsRemoteDataSource
import cl.getapps.githubjavarepos.features.repopullrequests.domain.repository.PullRequestsRepository
import io.reactivex.Completable
import io.reactivex.Single


class PullRequestsDataRepository(private val pullRequestsDataStoreFactory: PullRequestsDataSourceFactory) :
    PullRequestsRepository {

    override fun fetchPullRequests(params: PullRequestParams): Single<PullRequestsResponse> {
        return pullRequestsDataStoreFactory.retrieveCacheDataSource().isCached()
            .flatMap {
                pullRequestsDataStoreFactory.retrieveDataSource<PullRequestsRemoteDataSource>(it)
                    .fetchPullRequests(params)
            }
            .flatMap {
                save(it).toSingle { it }
            }

    }

    fun save(items: PullRequestsResponse): Completable {
        return pullRequestsDataStoreFactory.retrieveCacheDataSource().save(items)
    }

    fun clear(): Completable {
        return pullRequestsDataStoreFactory.retrieveCacheDataSource().clear()
    }
}
