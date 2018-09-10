package com.concrete.desafioandroid.data.repositories

import com.concrete.desafioandroid.data.models.OwnerDetails
import com.concrete.desafioandroid.data.models.PullRequest
import com.concrete.desafioandroid.data.models.RequestRepo
import com.concrete.desafioandroid.data.source.datasource.DataSource
import io.reactivex.Flowable


class ReposRepository(private val remoteDataSource: DataSource): DataSource {

    override fun getReposList(queries: HashMap<String, String>): Flowable<RequestRepo> {
        return remoteDataSource.getReposList(queries)
    }

    override fun getOwnerDetails(url: String): Flowable<OwnerDetails> {
        return remoteDataSource.getOwnerDetails(url)
    }

    override fun getPullsRequests(url: String): Flowable<List<PullRequest>> {
        return remoteDataSource.getPullsRequests(url)
    }
}