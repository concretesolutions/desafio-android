package cl.jesualex.desafio_android.search.data.remote

import cl.jesualex.desafio_android.search.data.domain.entity.SearchBase
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jesualex on 2019-05-28.
 */
interface SearchApi {
    @GET("repositories") fun search(
        @Query("q") lang: String,
        @Query("sort") sortBy: String,
        @Query("page") page: Int
    ): Observable<SearchBase>
}