package com.hako.githubapi.domain.usecases

import com.hako.githubapi.data.retrofit.RemoteDatasource
import com.hako.githubapi.domain.entities.PullRequest
import com.hako.githubapi.domain.requests.QueryPullRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.get

class GetPullRequests : KoinComponent {

    private val api: RemoteDatasource = get()

    fun execute(query: QueryPullRequest): Observable<List<PullRequest>> {
        return api.getPullsRequests(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}