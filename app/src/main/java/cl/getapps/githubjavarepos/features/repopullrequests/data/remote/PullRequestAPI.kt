package cl.getapps.githubjavarepos.features.repopullrequests.data.remote

import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.PullRequestsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface PullRequestAPI {

    companion object {
        private const val PULLREQUEST = "repos/{owner}/{repository}/pulls"
    }

    @GET(PULLREQUEST)
    fun fetchPullRequests(
        @Path("owner") owner: String,
        @Path("repository") repository: String
    ): Single<PullRequestsResponse>
}
