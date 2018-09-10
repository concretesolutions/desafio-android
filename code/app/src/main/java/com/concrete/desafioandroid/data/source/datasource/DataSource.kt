package com.concrete.desafioandroid.data.source.datasource

import com.concrete.desafioandroid.data.models.OwnerDetails
import com.concrete.desafioandroid.data.models.PullRequest
import com.concrete.desafioandroid.data.models.RequestRepo
import io.reactivex.Flowable


interface DataSource {

    fun getReposList(queries: HashMap<String, String>): Flowable<RequestRepo>

    fun getOwnerDetails(url: String): Flowable<OwnerDetails>

    fun getPullsRequests(url: String): Flowable<List<PullRequest>>

}