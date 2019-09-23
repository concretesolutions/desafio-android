package br.com.cavreti.desafio_android.network.service

import br.com.cavreti.desafio_android.data.PullRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface PullRequestService {

    @GET("repos/{ownerName}/{repoName}/pulls?state=all")
    fun getPullRequests(@Path("ownerName") ownerName: String, @Path("repoName") repoName: String, @Query("page") page : Int) : Observable<List<PullRequest>>
}