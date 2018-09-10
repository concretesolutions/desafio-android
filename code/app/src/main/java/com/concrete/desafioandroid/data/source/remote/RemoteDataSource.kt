package com.concrete.desafioandroid.data.source.remote.network

import com.concrete.desafioandroid.data.models.OwnerDetails
import com.concrete.desafioandroid.data.models.PullRequest
import com.concrete.desafioandroid.data.models.RequestRepo
import com.concrete.desafioandroid.data.source.datasource.DataSource
import io.reactivex.Flowable


class RemoteDataSource(private val restApi: RestApi): DataSource {

    override fun getReposList(queries: HashMap<String, String>): Flowable<RequestRepo> {
        return restApi.getReposList(queries)
    }

    override fun getOwnerDetails(url: String): Flowable<OwnerDetails> {
        return restApi.getOwnerDetails(url)
    }

    override fun getPullsRequests(url: String): Flowable<List<PullRequest>> {
        return restApi.getPullsRequests(url)
    }
}