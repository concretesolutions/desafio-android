package cl.getapps.githubjavarepos.feature.repopullrequests.data.remote

import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequest
import io.reactivex.Flowable
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
    ) : Flowable<List<PullRequest>>
}