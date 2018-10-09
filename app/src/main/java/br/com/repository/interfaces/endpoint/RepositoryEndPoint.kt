package br.com.repository.interfaces.endpoint

import br.com.repository.model.RepositoryResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryEndPoint {

    interface RepositoryByPage {
        @GET("search/repositories?q=language:Java&sort=stars")
        fun callRepositoryByPage(@Query("page") page: Int): Observable<RepositoryResult>
    }

    interface Repo {

    }

}