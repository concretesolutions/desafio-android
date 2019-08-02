package wilquer.lima.desafioconcrete.network.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import wilquer.lima.desafioconcrete.data.model.PullRequest

interface PullRequestService {

    @GET("repos/{creator}/{repo}/pulls")
    fun getPullRequests(@Path("creator") creator: String, @Path("repo") repo: String): Call<List<PullRequest>>
}