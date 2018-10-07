package com.joaoibarra.gitapp.api

import com.joaoibarra.gitapp.api.model.Result
import io.reactivex.Observable
import retrofit2.http.*

public interface GitApi {
    @Headers(
            "Accept: application/vnd.github.v3.text-match+json",
            "Content-type:application/json"
    )

    @GET("search/repositories")
    fun searchByQuery(@QueryMap searchQuery: HashMap<String, String>) : Observable<Result>
}