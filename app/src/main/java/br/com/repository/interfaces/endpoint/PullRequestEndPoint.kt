package br.com.repository.interfaces.endpoint

import br.com.repository.model.PullRequest
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestEndPoint {

    interface Pull {
        @GET("repos/{login}/{name}/pulls")
        fun callPullRequest(@Path("login") creator: String, @Path("name") name: String): Observable<List<PullRequest>>
    }


}