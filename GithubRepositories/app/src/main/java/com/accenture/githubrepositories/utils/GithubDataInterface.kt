package com.accenture.githubrepositories.utils

import com.accenture.githubrepositories.pojo.PullRequest
import com.accenture.githubrepositories.pojo.Repositories
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubDataInterface {

    @GET
    fun getRepositories(@Url url : String) : Flowable<Repositories>

    @GET
    fun getPullRequests(@Url url : String) : Observable<List<PullRequest>>
}