package contarini.com.desafio_tembici.data.service

import contarini.com.desafio_tembici.data.models.PullRequestResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestService {

    @GET("{owner}/{repo}/pulls")
    fun getIncidents(@Path("owner") creator : String,
                     @Path("repo") repository : String): Single<List<PullRequestResponse>>
}